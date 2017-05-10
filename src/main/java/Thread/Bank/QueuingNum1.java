package Thread.Bank;

import Thread.Bank.system.DoCall;



/**
 * Created by szh on 2017/5/8.
 */
public class QueuingNum1 extends QueuingMachine{

    @Override
    public void call() {
        //TODO 这里最好的做法是每次提交一个请求，然后后台就根据请求排号
        Runnable runnable =new DoCall();
        runnable.run();
    }
}