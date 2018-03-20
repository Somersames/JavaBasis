package reflec.proxy_test.ConstructTest;

public class Entity1 {
    static {
        System.out.println("static初始化");
    }

    public Entity1() {
    }

    int id;
    String name;
    public Entity1(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void say(){
        System.out.println(this.id);
        System.out.println(this.name);
    }
}
