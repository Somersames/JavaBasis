package nio2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by szh on 2017/5/6.
 */
public class MyPath {
    private void pa() throws IOException {
        Path path = Paths.get("D:\\JavaBasis\\新建文本文档.txt");
        File file =path.toFile(); //相当于调用new File(this.fileName);
        if(file.exists()){
            System.out.println("Y");
        }
//        FileReader fileReader = (FileReader) new InputStreamReader(new FileInputStream(file)); 这样转会出错,因为Filereader和InputStream都属于Reader的子类
        Reader fileReader =  new InputStreamReader(new FileInputStream(file));
        char[] c =new char[1024];
        int len =0;
        StringBuffer sb =new StringBuffer();
        if((len=fileReader.read(c)) != -1){
//            String s=new String(c,0,len);
            sb.append(new String(c,0,len));
        }
        System.out.println(sb.toString());
    }
    public static void main(String args[]) throws IOException {
        new MyPath().pa();
    }
}
