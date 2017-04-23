package reflec.aop.cglibtest;

/**
 * Created by szh on 2017/4/21.
 */
public class CglibTest {

public static void main(String args[]) throws ClassNotFoundException {
    Music music = new Music();
    CGLIBProxy cglibProxy = new CGLIBProxy(music);
    ((Music)cglibProxy.getProxy()).sing("ceshi de ");
}
}
