package Thread.Bank.system;

import Thread.Bank.QueuingNum1;
import Thread.Bank.QueuingNum2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * Created by szh on 2017/5/9.
 * 记住谁创建的线程池就该谁销毁，当main方法结束的时候假设线程池内的线程还未结束那么也会阻止jvm的退出
 */
public class InitBank {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    //创建两台叫号机
    private  BankSystem bankSystem;

    public InitBank() {
        bankSystem =new BankSystem();
    }

    private void init(){
        for(int i =0 ;i <20 ;i++){
            new QueuingNum1().call();
            new QueuingNum2().call();
        }
        System.out.println("队列是否为空"+Store.queue.isEmpty());
        while (!Store.queue.isEmpty()){
//            System.out.println("队列是否为空+！"+!Store.queue.isEmpty());
//            bankSystem.doSpend();
            this.executorService.execute(new PopWindows());
        }
//        System.out.println("已经执行到这里了");

    }
    public static void main(String args[]) throws InterruptedException {
        InitBank initBank =new InitBank();
        initBank.init();
        Thread.sleep(1000);
        if (Store.queue.isEmpty())
        initBank.executorService.shutdownNow();

    }
}
