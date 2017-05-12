package Thread.monitor;

import org.omg.CORBA.INTERNAL;

import java.util.Scanner;

/**
 * Created by szh on 2017/5/12.
 */
public class ClickTest {
    public static void main(String args[]){
        Scanner scanner =new Scanner(System.in);
        Click click =new Click();
        while (true){
            String line =scanner.nextLine();
            int id=Integer.parseInt(line);
            click.notifies(id);
        }
    }
}
