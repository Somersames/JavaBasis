package SetAndMap.TreeSetAndTreeMap;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * TreeSet的测试类
 *
 * @author szh
 * @create 2018-05-24 15:53
 **/
public class TestTreeSet{


    private static void  construct(){
        Comparator<String>  comparator =new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.toCharArray()[0] >o2.toCharArray()[0]){
                    return -1;
                }else if(o1.toCharArray()[0] == o2.toCharArray()[0]){
                    return 0;
                }else{
                    return 1;
               }
            }
        };
        TreeSet<String> treeSet =new TreeSet<>(comparator);
        List<String> list =new ArrayList<>();
        list.add("a");
        list.add("d");
        list.add("b");
        treeSet.addAll(list);
        Iterator<String> iterator =treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Comparator<String>  comparator1 = (Comparator<String>) treeSet.comparator();
        TreeSet<String> treeSet1 =new TreeSet<>(comparator1);
        treeSet1.add("c");
        treeSet1.add("g");
        treeSet1.add("a");
        Iterator<String> iterator1 =treeSet1.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
//        TreeSet<String> treeSet1 =treeSet.comparator();
//        Iterator<String> iterator2=treeSet1.iterator();
//        while (iterator2.hasNext()){
//            System.out.println(iterator.next());
//        }
    }

    private static void compareWithCpmparator(){
        Stack<Object> stack =new Stack<>();
        TreeSet<String> treeSet =new TreeSet<>();
        List<String> list =new ArrayList<>();
        list.add("a");
        list.add("d");
        list.add("b");
        treeSet.addAll(list);
        Iterator<String> iterator =treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Comparator<String>  comparator1 = (Comparator<String>) treeSet.comparator();
        if (comparator1 == null){
            System.out.println("comparator1是空");
        }else {
            System.out.println("comparator1不是空");
        }
    }

    private static void  com(){
        TreeSet<PojoTest> treeSet =new TreeSet<>();
        treeSet.add(new PojoTest(1,"a"));
        treeSet.add(new PojoTest(2,"b"));
        treeSet.add(new PojoTest(3,"c"));
        Iterator<PojoTest> iterator =treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getName());
        }
    }
    public static void main(String[] args) {
//        construct();
//        compareWithCpmparator();
        com();
    }

    @Test
    public void  calPoints() {
        String[] ops=new String[]{"5","-2","4","C","D","9","+","+"};
        Stack<Integer> stack =new Stack<>();
        int total=0;
        for(String s : ops ){
            if(s.equals("C")){
                total = total-stack.peek();
                stack.pop();
//                stack.push(total);
            }else if(s.equals("D")){
                int num =stack.peek();
                num =num *2;
                total+=num;
                stack.push(num);
            }else if(s.equals("+")){
                int str1=stack.pop();
                int str2 =stack.pop();
                int num =str1+str2;
                stack.push(str2);
                stack.push(str1);
                total =total+str1+str2;
                stack.push(num);
            }else{
                total+=Integer.valueOf(s);
                stack.push(Integer.valueOf(s));
            }

        }
        if(stack.size() <=1){
//            return Integer.valueOf(stack.pop());
            System.out.println(stack.pop());
        }
        System.out.println(total);
        // return Integer.valueOf(stack.pop())+Integer.valueOf(stack.pop());
    }

}
