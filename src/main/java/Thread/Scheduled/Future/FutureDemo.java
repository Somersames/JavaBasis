package Thread.Scheduled.Future;

import java.util.concurrent.*;

/**
 * Created by szh on 2017/5/10.
 */
public class FutureDemo {
    private ExecutorService executorService ;

    public FutureDemo() {
        executorService = Executors.newFixedThreadPool(3);
    }
    void read(){
        final Callable<String> task =new Callable<String>() {
            @Override
            public String call() throws Exception {
                for(int i=0 ;i<10 ;i++){
                    Thread.currentThread().sleep(i*200);
                    System.out.println(i+"---------");
                }
                return "OK";
            }
        };
        Future<String> future =executorService.submit(task);
        try{
            String s =future.get();
            System.out.println(s);
            executorService.shutdown(); // 结束该线程池
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        new FutureDemo().read();
    }
}
