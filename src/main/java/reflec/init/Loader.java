package reflec.init;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by szh on 2017/4/19.
 */
public class Loader {
    private Set<Class<?>> getClassSet(Set<Class<?>> set, String path, String packageName) throws FileNotFoundException {
        Set<Class<?>> classSet = set;
//        File file =new File(path);
        File[] fileDir = new File(path).listFiles();
        for (File file : fileDir) {
            if (file.isFile() && file.getName().endsWith(".class")) {
//                Class<?> clazz =loader_Class(packageName + "." + file.getName().substring(0,file.getName().lastIndexOf("class")-1),false);
                Class<?> clazz =loader_Class(packageName + "." + file.getName(),false);
                set.add(clazz); //添加包名和类名
            } else if (file.isDirectory()) {
                classSet=getClassSet(classSet, file.getAbsolutePath(), packageName + "." + file.getName());
            }
        }
//        if(!file.exists()){
//            throw new FileNotFoundException(path +" 文件路径出错");
//        }
//        if(!file.isDirectory() && file.isFile()){
//            if(file.getName().endsWith(".class")){
//                set.add(file.getName());
//            }
//        }
//        if(file.isDirectory()){
//            set=getClassSet(file.getAbsolutePath());
//        }
        return classSet;
    }
    //返回已经加载的类
    private Class<?> loader_Class(String className,boolean  initialization){
        Class<?> clazz=null;
        try{
            clazz=Class.forName(className,initialization,Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
    private String getPath() {
        return this.getClass().getResource("").getPath();
    }
    public static void main(String args[]) throws FileNotFoundException {
        Loader loader = new Loader();
        Set<Class<?>> set = new HashSet<>();
        Iterator<Class<?>> iterator = loader.getClassSet(set,loader.getPath(), "reflec").iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    public static Set<Class<?>> getBean(String packageName) throws FileNotFoundException {
        Loader loader = new Loader();
        Set<Class<?>> set = new HashSet<>();
        set=loader.getClassSet(set,loader.getPath(), packageName);
        return set;
    }
}
