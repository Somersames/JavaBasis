package Thread.elevator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by szh on 2017/5/10.
 * 在这里需要注意的是只有在通过线程的run或者strat方法调用的方法其Thread才不会是main
 */
public class EevatorControll {
    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    private static final int DEFAULT_NUM = 0; // 默认楼层
//    private Elevator elevator;
    static List<Elevator> elevatorList =new ArrayList<>();
    static List<Future<Integer>> futureList =new ArrayList<>();
//    private void Operation() {
//        Callable<Integer> callable = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return null;
//            }
//        };
//    }

//    public EevatorControll() {
//        elevator = new Elevator();
//    }

    public void receive(int id) throws InterruptedException, ExecutionException {
        if(futureList.isEmpty()){
            eleStart();
        }
        int[] i =new int[3];
        int temp=0;
        //这一步需要挑选出合适的线程来执行操作
//        for(Future<Integer> future :futureList){
//            i[temp]=future.get()-id;
//            temp++;
//        }
        for(Elevator elevator :elevatorList){
            i[temp] =elevator.getNowNum()-id; //当id小于当前楼层的时候
            System.out.println(temp+"-------------temp的值");
//            if(i[temp]>id-DEFAULT_NUM && elevator.getState()<=0){ //表示电梯在人的上面但是电梯在向下移动并且其距离小于底部触发的电梯
//
//            }else {
//                //开启一个新的电梯
//                eleStart();
//            }
            System.out.println(i.length+"i的长度");
            temp=temp+1;
        }
        int index =getMin(i); //获取数组里面较小的索引楼梯
        elevatorList.get(index).goTo(id); //去目的地
    }
    private void eleStart() throws InterruptedException {
        Elevator elevator =new Elevator();
        elevator.setFlag(true);
        elevator.setState(1); //1代表上升
//        elevator.elevatorUp(); 先不着急上升
        elevatorList.add(elevator);
        Future<Integer> future =executorService.submit(elevator);  //runnable可以直接被执行的
        futureList.add(future);
    }


    private int getMin(int[] i){
        int index =0;
        for(int m=1;m<i.length ;m++){
            if(i[m]<i[m-1] && i[m+1]!=0){
                System.out.println(i[m]+"---------i[m]");
                System.out.println(m+"----------m的值");
                index=m;
            }
        }
        return index;
    }
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        EevatorControll eevatorControll =new EevatorControll();
        Scanner scanner =new Scanner(System.in);
        while (true){
            String line =scanner.nextLine();
            int num =Integer.parseInt(line);
            eevatorControll.receive(num);
        }
    }
}
