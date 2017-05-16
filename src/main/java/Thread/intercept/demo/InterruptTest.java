package Thread.intercept.demo;

/**
 * Created by szh on 2017/5/16.
 */
public class InterruptTest extends  Thread {
    @Override
    public void run() {

        int i = 0;
        System.out.println(Thread.currentThread().isInterrupted());
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            i++;
        }
    }
    public static void main(String args[]) throws InterruptedException {
        Thread thread =new Thread(new InterruptTest());
        thread.start();
        Thread.currentThread().sleep(2);
        thread.interrupt();
    }

}
