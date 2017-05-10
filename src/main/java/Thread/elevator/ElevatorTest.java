package Thread.elevator;

/**
 * Created by szh on 2017/5/10.
 */
public class ElevatorTest {
    public static void main(String args[]){
        Elevator elevator =new Elevator();
        elevator.setNowNum(10);
        elevator.elevatorDown();
        System.out.println(elevator.getNowNum());
    }
}
