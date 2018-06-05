package ExceptionAndError;

/**
 * @author szh
 * @create 2018-05-25 16:08
 **/
public class TestErroe {
    Runnable runnable =new Runnable() {
        @Override
        public void run() {
            for(int i =0 ;i< 100000 ;i++){
//                if(i>50){
//                    try {
//                        Thread.currentThread().sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
    };
    private void startThread(){
        Thread thread =new Thread(runnable);
        thread.start();
        thread.stop();
    }

    public static void main(String[] args) {
        TestErroe testErroe =new TestErroe();
        testErroe.startThread();
        Thread.currentThread();
    }
}
