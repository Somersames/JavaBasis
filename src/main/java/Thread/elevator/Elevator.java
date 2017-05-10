package Thread.elevator;

/**
 * Created by szh on 2017/5/10.
 */
public class Elevator {
    private int NowNum;

    public Elevator() {
    }

    public int getNowNum() {
        return NowNum;
    }

    public void setNowNum(int nowNum) {
        NowNum = nowNum;
    }
    public void elevatorUp(){
        if(this.NowNum == 10){
            System.out.println("已经到达最高层");
            return;
        }
        this.NowNum=this.NowNum +1;
    }
    public void elevatorDown(){
        if(this.NowNum == 0){
            System.out.println("已到达最底层");
            return;
        }
        this.NowNum=this.NowNum - 1;
    }
}
