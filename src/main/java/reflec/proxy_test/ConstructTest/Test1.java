package reflec.proxy_test.ConstructTest;


import java.lang.reflect.InvocationTargetException;

public class Test1 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
//        Constructor<?> constructor = Test1.class.getConstructor(Entity1.class).newInstance(1,"2");
//        Entity1 en= Entity1.class.getConstructor(Integer.class,String.class).newInstance(1,"2");
//        Constructor<?> constructor =Entity1.class.getConstructor(Integer.class,String.class);
//        Entity1 en = (Entity1) constructor.newInstance(1,"2");
//        en.say();
        Entity1 en2=Entity1.class.getConstructor(int.class,String.class).newInstance(1,"2");
//        Class.forName("reflec.proxy_test.ConstructTest.Entity1").newInstance();
//        Test1.class.getClassLoader().loadClass("reflec.proxy_test.ConstructTest.Entity1");
//        en2.say();
    }

    private static void do1(Object object){
        System.out.println(object.getClass().getName().toString());
    }
}
