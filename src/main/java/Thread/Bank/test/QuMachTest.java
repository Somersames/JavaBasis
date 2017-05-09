package Thread.Bank.test;

import Thread.Bank.QueuingNum1;
import Thread.Bank.QueuingNum2;
import Thread.Bank.system.DoCall;
import Thread.Bank.system.Store;

/**
 * Created by szh on 2017/5/9.
 */
public class QuMachTest {
    public static void  main(String args[]) throws InterruptedException {
        for(int i=0 ;i< 5 ;i++){
            new QueuingNum1().call();
            new QueuingNum2().call();
        }
        Thread.sleep(1000);
        for(Integer i : Store.queue){
            System.out.println("插入之后的i==============="+i);
        }
    }
}
