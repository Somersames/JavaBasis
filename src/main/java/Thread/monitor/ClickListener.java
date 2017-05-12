package Thread.monitor;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by szh on 2017/5/12.
 */
public class ClickListener implements Runnable {
    private  Queue<Integer> queue =new ConcurrentLinkedDeque<>();
    @Override
    public void run() {
//       queue.add(this.getGotoNum()); // 将按得楼层添加到queue
        while (queue.size() !=0){
            System.out.println(queue.size()+"queue的size");
            int num =queue.poll();
            if (num > this.getId()){
                try {
                    goUp(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else{
                try {
                    goDown(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private int id;

    private boolean isRun;

    private int gotoNum;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGotoNum() {
        return gotoNum;
    }

    public void setGotoNum(int gotoNum) {
        this.gotoNum = gotoNum;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public ClickListener(int id) {
        this.id = id;
    }

    //该构造器方便在电梯运行的时候调整楼数
    public ClickListener() {
        this.id=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private void goUp(int num) throws InterruptedException {
        isRun =true;
        while (isRun) {
            System.out.println("当前电梯" +this.getName()+":::::当前线程"+ Thread.currentThread().getName() + "位于" + this.getId() + "正在往" + num + "运行");
            Thread.currentThread().sleep(1000);
            this.id = this.id + 1;
            if (this.id == num){
                System.out.println("到达第" + num + "层");
                isRun =false;
            }
        }
    }
    private void goDown(int num) throws InterruptedException {
        isRun=true;
        while (isRun) {
            System.out.println("当前电梯"+this.getName()+":::::当前线程" + Thread.currentThread().getName() + "位于" + this.getId() + "正在往" + num + "运行");
            Thread.currentThread().sleep(1000);
            this.id = this.id - 1;
            if (this.id == num){
                System.out.println("到达第" + num + "层");
                isRun =false;
            }
        }
    }

    public Queue<Integer> getQueue() {
        return queue;
    }
}
