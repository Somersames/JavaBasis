package reflec.proxy_test.cglib_test;

import net.sf.cglib.proxy.Enhancer;

public class CglibMain {
    public static void main(String[] args) {
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(Eat.class);
        enhancer.setCallback(new eatIntercept());
        Eat eat= (Eat) enhancer.create();
        eat.startEat();

    }
}
