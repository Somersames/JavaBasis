package Thread.intercept.demo;

/**
 * Created by szh on 2017/5/16.
 */
public class SleepTestThread extends  Thread {
    @Override
    public void run() {
       for(int i=0 ;i<100 ;i++){
           System.out.println(i);
           if(i>10){
               try {
//                   Thread.currentThread().interrupt();
//                   System.out.println(Thread.currentThread().isInterrupted()+"---------interrupt");
                   Thread.currentThread().sleep(1000);
                   System.out.println(Thread.currentThread().isInterrupted());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
    }
    public void cancle(){
        interrupt();
    }
}
