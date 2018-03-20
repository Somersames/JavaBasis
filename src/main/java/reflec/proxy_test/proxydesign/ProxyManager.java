package reflec.proxy_test.proxydesign;

public class ProxyManager implements Boss {
    private Boss boss;
    @Override
    public void say() {
        if (boss == null){
            boss =new RealBoss();
        }
        boss.say();
    }
}
