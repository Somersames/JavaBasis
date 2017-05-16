package Thread.elvgithub;

/**
 * Created by szh on 2017/5/11.
 */
public class Main {
    public static void main(String[] args)
    {
        ElevatorUserInterface userInterface = new ElevatorUserInterface();
        ElevatorController.init(userInterface);
        userInterface.setVisible(true);
    }
}
