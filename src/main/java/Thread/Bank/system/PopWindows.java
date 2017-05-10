package Thread.Bank.system;


import java.util.Queue;

/**
 * Created by szh on 2017/5/9.
 */
public class PopWindows implements  Runnable{
    @Override
    public void run() {
        try {
            spend();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void spend() throws InterruptedException {
        if(!Store.queue.isEmpty()) {
            int nowNUm = Store.queue.poll();
            System.out.println("线程：" + Thread.currentThread().getName() + "正在为" + nowNUm + "服务");
            Thread.currentThread().sleep(1000);
        }else{
            System.out.println("当前一经没有客户");
        }
    }
}
