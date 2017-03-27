package File;

import java.io.*;

/**
 * Created by szh on 2017/3/27.
 */
public class FileTest {
    /*
    文件以字节流读取
    1.以这种方式读取非文档的
     */
    public static void tes() throws IOException {
//        File file1 =new File("D:\\disk\\1\\Java锁.pdf");
//        File file2 =new File("D:\\disk\\tes.pdf");
//        File file1 =new File("D:\\disk\\1\\Java锁.txt");
//        File file2 =new File("D:\\disk\\tes.txt");
//        File file1 =new File("D:\\disk\\1\\Unit.wma");
//        File file2 =new File("D:\\disk\\tes.wma");
        File file1 =new File("D:\\disk\\1\\README.doc");
        File file2 =new File("D:\\disk\\tes.doc");
        Reader reader =new InputStreamReader(new FileInputStream(file1));
        int len;
        char[] a =new char[1024];
        StringBuffer sb =new StringBuffer();
        while ((len=reader.read(a))!= -1)  //以字节流去读pdf文件
        {
            sb.append(new String(a,0,len));
        }
        reader.close();
        FileWriter fileWriter = new FileWriter(file2); // 将读取出来的额String数据直接写入
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();
    }
    /*
    以字节流来读取文件
     */
    public static void test2() throws IOException {
//        File file1 =new File("D:\\disk\\1\\Java锁.pdf");
//        File file2 =new File("D:\\disk\\test2.pdf");
//        File file1 =new File("D:\\disk\\1\\Java锁.txt");
//        File file2 =new File("D:\\disk\\test2.txt");
//        File file1 =new File("D:\\disk\\1\\Unit.wma");
//        File file2 =new File("D:\\disk\\test2.wma");
        File file1 =new File("D:\\disk\\1\\README.doc");
        File file2 =new File("D:\\disk\\test2.doc");
        byte[] sendBytes = new byte[1024];
        int length = 0;
        StringBuffer sb =new StringBuffer();
        FileInputStream fileInputStream =new FileInputStream(file1);
        while((length = fileInputStream.read(sendBytes, 0, sendBytes.length)) > 0){  // 将文件读取为String
            sb.append(new String(sendBytes, 0, length));
        }
        FileWriter fileWriter = new FileWriter(file2);     // 这里会导致读取出来的txt文字都是拷斤棍
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();
    }
    /*
    关于FileWriter和FileOutPutStream来写入数据的差别，
    FileWriter是直接写入Text到文件里面去的，类似记事本直接写入文字
    FileOutPutStream则是写入的是原始数据，类似，你需要将音乐文件原封不动的复制，则适应FileWriter可能会导致错误
     */
    /*
    以字节流读取但是不读取为String
     */
    public  static  void copy() throws IOException {
//        File file1 =new File("D:\\disk\\1\\Java锁.pdf");
//        File file2 =new File("D:\\disk\\copy.pdf");
//        File file1 =new File("D:\\disk\\1\\Java锁.txt");
//        File file2 =new File("D:\\disk\\copy.txt");
//        File file1 =new File("D:\\disk\\1\\Unit.wma");
//        File file2 =new File("D:\\disk\\copy.wma");
        File file1 =new File("D:\\disk\\1\\README.doc");
        File file2 =new File("D:\\disk\\copy.doc");
        InputStream in =null;
        OutputStream out =null;
        try{
            in= new FileInputStream(file1);
            out=new FileOutputStream(file2);
            byte[] bytes= new byte[1024];
            int read =0;
            StringBuffer sb =new StringBuffer();
            while ((read =in.read(bytes))!= -1)
            {
                out.write(bytes,0,read);    // 直接以字节流的方式输出到文件
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
            out.close();
        }
    }
    /*
    将文件以字节流的方式读取文件转为String但是又重新将String转为字节流然后写入
     */
    public static void test1() throws IOException {
//        File  file1= new File("D:\\disk\\1\\Java锁.pdf");
//        File file2 =new File("D:\\disk\\test1.pdf");
//        File file1 =new File("D:\\disk\\1\\Java锁.txt");
//        File file2 =new File("D:\\disk\\test1.txt");
//        File file1 =new File("D:\\disk\\1\\Unit.wma");
//        File file2 =new File("D:\\disk\\test1.wma");
        File file1 =new File("D:\\disk\\1\\README.doc");
        File file2 =new File("D:\\disk\\test1.doc");
        InputStream in =null;
        OutputStream out =null;
        try{
            in= new FileInputStream(file1);
            byte[] bytes= new byte[1024];
            int read =0;
            StringBuffer sb =new StringBuffer();
            while ((read =in.read(bytes))!= -1)
            {
                sb.append(new String(bytes,0,read));
            }
            System.out.println("a");
            InputStream inputStream =new ByteArrayInputStream(sb.toString().getBytes());
            byte[] re =new byte[1024];
            int haveRead= 0;
            FileOutputStream fileOutputStream =new FileOutputStream(file2);
            while ((haveRead =inputStream.read(re))!= -1)
            {
                fileOutputStream.write(re,0,haveRead);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            in.close();
        }

    }
    /*
    修改编码
     */
    public static void test3() {
//        File file1 =new File("D:\\disk\\1\\Java锁.pdf");
//        File file2 =new File("D:\\disk\\copy.pdf");
//        File file1 = new File("D:\\disk\\1\\Java锁.txt");
//        File file2 = new File("D:\\disk\\test3.txt");
//        File file1 =new File("D:\\disk\\1\\Unit.wma");
//        File file2 =new File("D:\\disk\\test3.wma");
        File file1 =new File("D:\\disk\\1\\README.doc");
        File file2 =new File("D:\\disk\\test3.doc");
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(file1);
            out = new FileOutputStream(file2);
            byte[] bytes = new byte[1024];
            int read = 0;
            StringBuffer sb = new StringBuffer();
            while ((read = in.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, read));
            }
            InputStream inputStream = new ByteArrayInputStream(sb.toString().getBytes());
            byte[] re = new byte[1024];
            int haveRead = 0;
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            StringBuffer sb2 = new StringBuffer();
            while ((haveRead = inputStream.read(re)) != -1) {
//                fileOutputStream.write(re,0,haveRead);
                sb2.append(new String(re, 0, haveRead));
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");  // 在这里转换的话会导致都是？
            outputStreamWriter.write(sb2.toString());
            outputStreamWriter.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
        }
    }

    public static void test4() throws IOException {
//        File file1 =new File("D:\\disk\\1\\Java锁.txt");
//        File file2 =new File("D:\\disk\\test4.txt");
//        File file1 =new File("D:\\disk\\1\\Unit.wma");
//        File file2 =new File("D:\\disk\\test4.wma");
        File file1 =new File("D:\\disk\\1\\README.doc");
        File file2 =new File("D:\\disk\\test4.doc"); //拷斤棍
        FileInputStream fileInputStream =new FileInputStream(file1);
        Reader reader =new InputStreamReader(fileInputStream,"GBK"); // txt测试GBK结果正确 DOC测试以UTF-8测试结果：拷斤棍 //
        char[] chars =new char[1024];
        int read=0;
        StringBuffer sb =new StringBuffer();
        while((read=reader.read(chars))!= -1)
        {
            sb.append(new String(chars,0,read));
        }
        FileOutputStream fileOutputStream =new FileOutputStream(file2);
        Writer writer =new OutputStreamWriter(fileOutputStream,"GBK");
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }


    public static void main(String args[]) throws IOException {
        tes();
        test2();
        copy();
        test1();
        test3();
        test4();
    }


}

