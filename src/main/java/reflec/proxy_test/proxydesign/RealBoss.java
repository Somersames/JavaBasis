package reflec.proxy_test.proxydesign;

public class RealBoss implements Boss {
    @Override
    public void say() {
        System.out.println("真Boss");
    }
}
