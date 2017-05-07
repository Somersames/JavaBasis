package nio2.path;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by szh on 2017/5/7.
 */
public class DirPathTest {
    private void dirte(){
        Path dir = Paths.get("D:\\disk");
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir,"*.doc")){
            for (Path entry :stream){
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        new DirPathTest().dirte();
    }
}
