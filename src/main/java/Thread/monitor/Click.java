package Thread.monitor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by szh on 2017/5/12.
 */
public class Click {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    static  List<ClickListener> listenerList =new ArrayList<>();
    static int num=1;
    public ClickListener add(int clickedNum){
//        listenerList.add(clickListener);
        ClickListener clickListener =new ClickListener();
        clickListener.setName("电梯"+num+"号");
//        executorService.execute(clickListener);
        listenerList.add(clickListener); //加入一个正在运行的
        num++;
        return clickListener;
    }
    public void tell(int clickedNum){
        notifies(clickedNum);
    }
    public void notifies(int clickedNum){
//        ClickListener clickListener=each(clickedNum); // 取出最合适的电梯
        ClickListener clickListener =foreach(clickedNum);
        clickListener.getQueue().add(clickedNum);
        executorService.execute(clickListener);
//        clickListener.sysRun(clickedNum);
//        clickListener.getQueue().add(clickedNum);
    }
    //这里应该再写一个遍历算法挑选出最合适执行任务的电梯
    private  synchronized ClickListener each(int clickedNum){
        Iterator<ClickListener> iterator =listenerList.iterator();
        int temp =0;
        int depth=0;
        ClickListener returnClick=null;
        if (listenerList.size() == 0){
            return add(clickedNum);
        }
        while(iterator.hasNext()){
            if(listenerList.size() == 0){
                return iterator.next();
            }else
                {
                ClickListener clickListener =iterator.next();
                int itTemp=clickListener.getId();
                if(Math.abs(itTemp -clickedNum) < Math.abs(clickListener.getId() -clickedNum)){// 下一个电梯比第一个距离近
                    temp=itTemp;
                    returnClick =clickListener;
                }else if(Math.abs(0-clickedNum) <Math.abs(temp-clickedNum ) && listenerList.size() <5){ // 判断0和当前楼层的距离且在运行的电梯需要小于5
                    temp=itTemp;
                    returnClick=add(clickedNum);   //重新生成一个电梯
                }
                }
        }
        return returnClick;
    }
    @Test
    public void ty(){
        int size =1;
        for(int i=1; i<size;i++){
            System.out.println("OK");
        }
    }

    public ClickListener foreach(int num) {
        int size = listenerList.size();
        ClickListener[] clickListeners= new ClickListener[5];
        for(int m=0;m<size ;m++){
            clickListeners[m]=listenerList.get(m);
        }
//        ClickListener[] clickListeners = (ClickListener[]) listenerList.toArray(); 抛出类型转换异常
        int min = 0;
        if (listenerList.size() == 0) {
            return add(num);
        } else if (listenerList.size() == 1) {
            if (Math.abs(0 - num) < Math.abs(clickListeners[0].getId() - num)) {
                return add(num);
            } else {
                return clickListeners[0];
            }
        }
        for (int i = 1; i < size; i++) {
            if (Math.abs(clickListeners[i].getId()-num) < Math.abs(clickListeners[min].getId()-num)) {
                min = i;
            }
        }
        if (Math.abs(0 - num) < Math.abs(clickListeners[min].getId() - num)) {
            return add(num);
        } else {
            return clickListeners[min];
        }
    }
}
