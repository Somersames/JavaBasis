package reflec.test;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by szh on 2017/4/19.
 */
public class Init_a_Test extends ClassLoader{
    public  static void te()
    {
        try{
            Class.forName("reflec.test.Init_a",true,Thread.currentThread().getContextClassLoader()); // 会初始化static块
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void tes() throws ClassNotFoundException {
        ClassLoader.getSystemClassLoader().loadClass("reflec.test.Init_a");  //默认不初始化
        /*
        laodClass(String ClassName,boolean init)是protected方法
         */
    }
    public static void main(String args[]) throws ClassNotFoundException, IOException {
//        tes();

//        getPath();
        Init_a_Test i =new Init_a_Test();
        Iterator<Class<?>> iterator=i.getPath("reflec").iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    public  Set<Class<?>> getPath(String pack) throws IOException {
        Set<Class<?>> set =new HashSet<>();
        Enumeration<URL> path =Thread.currentThread().getContextClassLoader().getResources(pack);
        while (path.hasMoreElements()){
            URL url =path.nextElement();
            if(url != null){
                String protocal =url.getProtocol();
                if(protocal.equals("file")){
                    String packagePath =url.getPath().replaceAll("%20"," ");
//                    System.out.println(packagePath+"-------------packagePath");
                    set=enumClass(set,packagePath,pack);
                }else if(protocal.equals("jar")){
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    if(jarURLConnection != null){
                        JarFile jarFile =jarURLConnection.getJarFile();
                        if(jarFile != null){
                            Enumeration<JarEntry> jarEntries =jarFile.entries();
                            while (jarEntries.hasMoreElements()){
                                JarEntry jarEntry =jarEntries.nextElement();
                                String jarEntyyName =jarEntry.getName();
                                if(jarEntyyName.endsWith(".class")){
                                    String className =jarEntyyName.substring(0,jarEntyyName.lastIndexOf("."));
//                                    System.out.println(className+"---------------jar里面的");
                                }
                            }
                        }
                    }
                }
            }
        }
        return set;
    }
    private  Set<Class<?>> enumClass(Set<Class<?>> set,String FilePath,String packageName){
        File file =new File(FilePath);
        File[] files = file.listFiles();
            for(File ListFile :files){
                if(ListFile.isFile() && ListFile.getName().endsWith(".class")){
                    String className =ListFile.getName().substring(0,ListFile.getName().lastIndexOf("."));
                    Class<?> clazz=load_cl(packageName+"."+className,false);
//                    System.out.println(packageName+"."+className+"-----------fileName");
                    set.add(clazz);
                } else if(file.isDirectory()){
//                    System.out.println(file.getAbsolutePath()+"--------------file.getAbsolutePath()");
//                    System.out.println(file.getAbsolutePath() + "\\" + ListFile.getName()+"------------file.getAbsolutePath()+\"\\\\\"+file.getName()");
                    set =enumClass(set,file.getAbsolutePath()+"\\"+ListFile.getName(),packageName+"."+ListFile.getName());
                }
            }
            return set;
    }
    private Class<?> load_cl(String path,boolean init){
        Class<?> clazz=null;
        try{
            clazz =Class.forName(path,init,Thread.currentThread().getContextClassLoader()); // false避免初始化
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
