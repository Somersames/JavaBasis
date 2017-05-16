package Thread.intercept.demo;

/**
 * Created by szh on 2017/5/16.
 */
public class SleepTest {
    private void test(){

    }
    public static void  main(String args[]) throws InterruptedException {
        SleepTestThread sleepTestThread= new SleepTestThread();
        Thread thread =new Thread(sleepTestThread);
        thread.start();
        Thread.currentThread().sleep(3000);
        sleepTestThread.cancle();
    }
}
