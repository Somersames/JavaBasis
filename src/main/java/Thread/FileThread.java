package Thread;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by szh on 2017/3/27.
 * 测试在多线程的情况下普通IO的读写情况
 * 在未加入flush方法的时候一直写不到10000，需要主要每次写入
 */
public class FileThread {
    public static void main(String args[]) throws InterruptedException, IOException {
        FileInOut fileInOut1 =new FileInOut("D:\\disk\\qqq.txt","第一个");
        FileInOut fileInOut2 =new FileInOut("D:\\disk\\qqq.txt","第二个");
        new Thread(fileInOut1).start();
        new Thread(fileInOut2).start();
        Thread.sleep(5000);
        System.out.println("已经推出");
//        wr();
    }

    public static void wr() throws IOException {
        int t = 0;
        File file = new File("D:\\disk\\qqq.txt");
        Writer writer = new FileWriter(file);
        while (t < 10000) {
            writer.write(t + Thread.currentThread().getName());
            writer.flush();
            t++;
        }
        writer.close();
    }

}

class FileInOut extends Thread {
    String path;
    String content;

    public FileInOut(String path, String content) {
        this.path = path;
        this.content = content;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                reou(this.path, this.content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void reou(String path, String content) throws IOException, InterruptedException {
        Writer writer = new FileWriter(new File(path),true);
//        String s1="he";
//        writer.write(s1+content);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()) + "-----------" + Thread.currentThread().getName());
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (i < 10000) {
            synchronized (writer) {
                sb.append("0");
                writer.write(i + Thread.currentThread().getName()+"\r\n");
               writer.flush();
                i++;
            }
        }
        System.out.println(i);
        System.out.println(sb.toString().length() + "sb的值");
        System.out.println(simpleDateFormat.format(new Date()) + "-----------" + Thread.currentThread().getName());
//        String s2="llo"+content;
//        writer.write(s2);
//        writer.close();
    }
}
