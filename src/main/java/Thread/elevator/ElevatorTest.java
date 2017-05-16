package Thread.elevator;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by szh on 2017/5/10.
 */
public class ElevatorTest {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    public static void main(String args[]) throws InterruptedException, ExecutionException {
//        Elevator elevator =new Elevator();
//        elevator.setNowNum(10);
//        elevator.elevatorDown();
//        System.out.println(elevator.getNowNum());
        ElevatorTest elevatorTest =new ElevatorTest();
        elevatorTest.do2();

    }
    private void do1() throws ExecutionException, InterruptedException {
        EevatorControll eevatorControll =new EevatorControll();
        Scanner scanner =new Scanner(System.in);
        while (true){
            String line =scanner.nextLine();
            int num =Integer.parseInt(line);
            eevatorControll.receive(num);
        }
    }
    private void do2() throws InterruptedException {
        EevatorControll eevatorControll =new EevatorControll();
        Scanner scanner =new Scanner(System.in);
        while (true){
            String line =scanner.nextLine();
            int num =Integer.parseInt(line);
            eevatorControll.eleStart(num);
        }
    }
}
