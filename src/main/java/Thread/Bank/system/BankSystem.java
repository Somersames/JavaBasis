package Thread.Bank.system;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by szh on 2017/5/9.
 * bug1：当那边while已经结束后这里的线程池还在提交任务：猜测：线程池一经开启则与调用者无关
 */
public class BankSystem {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    public void doSpend() {
        if (Store.queue.isEmpty()) {
            executor.shutdownNow();
        } else {
//            executor.execute(new ServiceWindows(Store.queue.poll())); // 执行操作任务
        }
    }
}
