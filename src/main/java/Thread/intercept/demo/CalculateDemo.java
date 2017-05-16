package Thread.intercept.demo;


/**
 * Created by szh on 2017/5/15.
 */
public class CalculateDemo extends  Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        while(!Thread.currentThread().isInterrupted()) {
            for (int i = 0; i < 50; i++) {
                System.out.println(i + "--------------i");
                if (i > 30) {
                    try {
                        Thread.currentThread().sleep(500);
                        Thread.currentThread().interrupt();
                        System.out.println(Thread.currentThread().isInterrupted());
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("已被中断");
//                    break;
                    }
                }
            }
        }
    }
}
