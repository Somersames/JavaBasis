package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.in;

/**
 * Created by szh on 2017/3/28.
 */
public class FileServer {
    ServerSocket serverSocket=null;
    public  void se() throws IOException {
        while (true)
        {
            Socket socket =serverSocket.accept();
            InputStream in =socket.getInputStream();
//            DataInputStream dataInputStream =new DataInputStream(in);
//            FileOutputStream fileOutputStream =new FileOutputStream(new File("D:\\JavaBasis\\java锁.pdf"));
//            byte[] bytes =new byte[1024];
//            int le;
//            while ((le= dataInputStream.read(bytes))!= -1)
//            {
//                fileOutputStream.write(bytes,0,le);
//                fileOutputStream.flush();
//            }
            ServerThread serverThread =new ServerThread(in);
            Thread thread =new Thread(serverThread);
            thread.start();
        }
    }

    public FileServer() {
        try {
            serverSocket =new ServerSocket(9010);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws IOException {
        FileServer fileServer =new FileServer();
        fileServer.se();
    }
}
class  ServerThread extends Thread {
    InputStream inputStream;

    public ServerThread(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try {
            the(this.inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void the(InputStream in) throws IOException {
        DataInputStream dataInputStream =new DataInputStream(in);
        FileOutputStream fileOutputStream =new FileOutputStream(new File("D:\\JavaBasis\\java锁1.pdf"));
        byte[] bytes =new byte[1024];
        int le;
        while ((le= dataInputStream.read(bytes))!= -1)
        {
            fileOutputStream.write(bytes,0,le);
            fileOutputStream.flush();
        }
    }
}
