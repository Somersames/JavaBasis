package reflec.aop.Javasist;

import javassist.*;

/**
 * javasistMain方法
 *
 * @author szh
 * @create 2018-03-30 20:17
 **/
public class JavasistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
                CtClass ctClass= ClassPool.getDefault().get("reflec.aop.Javasist.JavaisitSing");
                String procyName="Sing";
                CtMethod ctMethod=ctClass.getDeclaredMethod(procyName);
                String newName=procyName+"$NewImpl";
                ctMethod.setName(newName);
                CtMethod newMethod= CtNewMethod.copy(ctMethod, "Sing", ctClass, null);
                StringBuffer sb=new StringBuffer();

                sb.append("{System.out.println(\"开始唱歌前的准备\");\n")
                 .append(newName+"($$);\n")
                  .append("System.out.println(\"唱歌结束\");\n}");
               newMethod.setBody(sb.toString());
               ctClass.addMethod(newMethod);
               JavaisitSing a=(JavaisitSing)ctClass.toClass().newInstance();
                a.Sing();
    }
}
