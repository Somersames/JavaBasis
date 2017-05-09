package Thread.Bank;

import Thread.Bank.system.DoCall;

/**
 * Created by szh on 2017/5/8.
 */
public class QueuingNum2 extends QueuingMachine{
    @Override
    public void call() {
        Runnable runnable =new DoCall();
        runnable.run();
    }
}
