package Thread.elevator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by szh on 2017/5/10.
 */
public class Elevator implements Runnable{
    private static EevatorControll eevatorControll;
    private int NowNum;
    private int state; // 1代表上升 0代表停止 -1代表下降
    private boolean flag; //代表启动了电梯
    public Elevator() {
        eevatorControll=new EevatorControll();
        NowNum=0;
    }
    private int id;

    public Elevator(int id) {
        this.id = id;
        eevatorControll=new EevatorControll();
        NowNum=0;
    }

    public int getNowNum() {
        return NowNum;
    }

    public void setNowNum(int nowNum) {
        NowNum = nowNum;
    }
    public void elevatorUp(int id) throws InterruptedException {
        if(this.NowNum == 10){
            System.out.println("已经到达最高层");
            return;
        }
        while (flag) {
            this.NowNum = this.NowNum + 1;
            System.out.println(Thread.currentThread().getName() + "上升到了" + this.NowNum);
            Thread.currentThread().sleep(1000);
//            if(this.NowNum == id && id<=10 && this.NowNum <=10){ //
//                this.flag =false;
//                this.setState(0);
//            }else if(this.NowNum>10){
//                System.out.println("楼层超出最大范围");
//                this.flag =false;
//                this.setState(0);
//            }
            if(this.NowNum>=10 && this.getState()==1){
                System.out.println("楼层超出最大范围");
                this.flag =false;
                this.setState(0);
            }else if(this.NowNum == id && id<=10 && this.NowNum <=10){ //
                this.flag =false;
                this.setState(0);
            }

        }
    }
    public void elevatorDown(int id) throws InterruptedException {
        if(this.NowNum == 0){
            System.out.println("已到达最底层");
            return;
        }
        while (flag) {
            this.NowNum = this.NowNum - 1;
            System.out.println(Thread.currentThread().getName() + "上升到了" + this.NowNum);
            Thread.currentThread().sleep(1000);
            if(this.NowNum == id){
                this.flag =false;
                this.setState(0);
            }else if(this.NowNum<0){
                System.out.println("楼层超出最小范围");
                this.flag =false;
                this.setState(0);
            }
        }
    }
//    @Override
//    public Integer call() throws Exception { //需要在这里执行一系列的操作
////        eevatorControll.receive();
////        return this.getNowNum();
//        eevatorControll.receive(this.id);
//        return this.getNowNum();
//    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public  void goTo(int id) throws InterruptedException {
        int now=this.getNowNum();
        this.flag=true;
        if(now>id){
            elevatorDown(id);
        }else{
            elevatorUp(id);
        }
//        while (flag) {
//            if (this.getNowNum() == id) {
//                this.setFlag(false);
//                this.setState(0);
//            }
//        }
    }

    @Override
    public void run() {
        try {
            eevatorControll.receive(this.id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
