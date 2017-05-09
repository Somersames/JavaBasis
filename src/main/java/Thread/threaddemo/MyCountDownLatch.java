package Thread.threaddemo;

import java.util.concurrent.CountDownLatch;

/**
 * Created by szh on 2017/4/2.
 */
public class MyCountDownLatch {
    private CountDownLatch countDownLatch = new CountDownLatch(3);

    public void test() {
        new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        }.run();

        new Runnable(){
            @Override
            public void run() {
                System.out.println("b");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        }.run();
        new Runnable(){
            @Override
            public void run() {
                System.out.println("c");
                countDownLatch.countDown();
            }
        }.run();
    }

    public static void main(String args[]) throws InterruptedException {
    MyCountDownLatch myCountDownLatch= new MyCountDownLatch();
        System.out.println("d");
        myCountDownLatch.test();
        myCountDownLatch.countDownLatch.await();
        System.out.println("e");
    }
}
