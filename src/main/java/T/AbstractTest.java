package T;

/**
 * @author szh
 * @create 2018-05-28 0:10
 **/
public abstract class AbstractTest {
    public void methodWithBody(){
        System.out.println("body");
    }
    public abstract void methodWithoutBody();
    protected void protectWithBody(){
        System.out.println("protectBody");
    }
    protected abstract void ProtectWithoutBody();
    private void privateWithBody(){
        System.out.println("protectBody");
    }
    abstract void defaultWithOutMethod();
    void defaultWithBody(){

    }
    public int i;
    protected int pi;
    private int privatei;
    int defaulti;

    public static void main(String[] args) {
        AbstractTest abstractTest =new AbstractTest() {
            @Override
            public void methodWithoutBody() {

            }

            @Override
            protected void ProtectWithoutBody() {

            }

            @Override
            void defaultWithOutMethod() {

            }
        };
    }



}
