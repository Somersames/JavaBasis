package Thread.intercept.demo;

/**
 * Created by szh on 2017/5/15.
 */
public class DemoMain {
    public static  void main(String args[]) throws InterruptedException {
//        Thread thread = new Thread(new CalculateDemo());
//        thread.start();
        CalculateDemo calculateDemo =new CalculateDemo();
        calculateDemo.start();
        Thread.currentThread().sleep(2000);
//        thread.stop();
//        thread.interrupt(); //
//        calculateDemo.interrupt(); // 需要在线程内部设置终端点，外部设置没用
        System.out.println(calculateDemo.isInterrupted()+"-----------main");
//        Thread.currentThread().sleep(5000);
//        System.out.println(thread.isInterrupted()+"------2");
    }
}