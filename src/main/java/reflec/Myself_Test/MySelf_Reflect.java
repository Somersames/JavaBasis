package reflec.Myself_Test;

/*
利用反射分别修改 static final 的声明和对象
前一个方法代表修改变量，后一个为对象
*/
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MySelf_Reflect {
    public void doTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class clazz =  Class.forName("reflec.Myself_Test.MySelf_Basic_Num");
        Field field =clazz.getDeclaredField("Sum_1");
        field.setAccessible(true);
        Field modifiers =field.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field,field.getModifiers() & ~Modifier.FINAL);
        MySelf_Basic_Num mySelf_basic_num = (MySelf_Basic_Num) clazz.newInstance();
        field.set(mySelf_basic_num,3456);
        Object object =field.get(mySelf_basic_num);
        System.out.println(object.toString());
        mySelf_basic_num.getSum_1();
//        field.set(mySelf_basic_num,90);
//        System.out.println(field.hashCode());
//        field.set(mySelf_basic_num,900);
//        mySelf_basic_num.getSum_1();
//        Integer integer1 = (Integer) object;
//        int sum_1 =integer1.intValue();
//        sum_1++;
    /*    Field field1 =clazz.getDeclaredField("Integer_sum");
        field.setAccessible(true);
        Object Integer_Value =field1.get(mySelf_basic_num);
        System.out.println(Integer_Value.hashCode());
        Integer integer = (Integer) Integer_Value;
        int result = integer.intValue();
        System.out.println(Integer_Value.hashCode());
        result++;
        System.out.println(result);*/
    /*Field field1 =clazz.getDeclaredField("Sum_2");

    field1.setAccessible(true);
        field1.setInt(field1, field1.getModifiers() & ~Modifier.FINAL);
        field1.set(null,19999);
    Object object2 = field1.get(mySelf_basic_num);

//        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

    field1.set(object2,12345);
    System.out.println(object2.toString());

    Field field3 =clazz.getDeclaredField("Integer_sum");
        field3.setAccessible(true);
        Object object3 =field3.get(mySelf_basic_num);
        field.set(object3,999);
        System.out.println(object3.toString());*/

//        result++;
//        System.out.println(Integer_Value.toString());
    }
    private void modifi() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class clazz =Class.forName("reflec.Myself_Test.MySelf_Basic_Num");
        Field field =clazz.getDeclaredField("Sum_2");
        field.setAccessible(true);
        Field modifiers =field.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field,field.getModifiers() & ~Modifier.FINAL);
        field.set(null,13);
        MySelf_Basic_Num mySelf_basic_num = (MySelf_Basic_Num) clazz.newInstance();
        mySelf_basic_num.getSum_2();
    }
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        MySelf_Reflect mySelf_reflect =new MySelf_Reflect();
        mySelf_reflect.doTest();
//        mySelf_reflect.modifi();
    }
}
