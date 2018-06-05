package PECS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author szh
 * @create 2018-06-01 11:17
 **/
public class PECSTest {
    private void t(){
        List<? extends A> list =new ArrayList<>();
        B b = new B();
        A  a =new A();
    }
}

class A{

}
class B extends  A{
    public void b(){
        System.out.println("b");
    }
}