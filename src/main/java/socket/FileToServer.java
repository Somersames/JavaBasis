package socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by szh on 2017/3/28.
 */
public class FileToServer {
    public static void fi() throws IOException {
        Socket socket =new Socket("127.0.0.1",9010);
        OutputStream outputStream =socket.getOutputStream();
        DataOutputStream dataOutputStream =new DataOutputStream(outputStream);
        FileInputStream fileInputStream =new FileInputStream(new File("D:\\disk\\1\\java锁.pdf"));
        byte[] bytes =new byte[1024];
        int len =0;
        while ((len=fileInputStream.read(bytes))!= -1)
        {
            dataOutputStream.write(bytes,0,len);
            dataOutputStream.flush();
        }
    }
    public static void main(String args[]) throws IOException {
        fi();
    }

}
