package Thread.elvgithub;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by szh on 2017/5/11.
 */
public class ElevatorController implements Runnable{
    private static ElevatorUserInterface userInterface;
    private static ElevatorController[] controllers = new ElevatorController[ElevatorUserInterface.elevatorNumber];


    public static final int DEST = 1;
    public static final int SRC = -1;


    static void init(ElevatorUserInterface ui)
    {

        ElevatorController.userInterface = ui;
        FloorSelectorListener.userInterface = ui;
        for (int i = 0; i < ElevatorUserInterface.elevatorNumber; i++)
        {
            controllers[i] = new ElevatorController(ui.elevatorSet[i]);
        }
        ControlPanelListener controlPanelListener = new ControlPanelListener();
        FloorSelectorListener floorSelectorListener = new FloorSelectorListener();

        for (int i = 0; i < ElevatorUserInterface.elevatorNumber; i++)
        {

            userInterface.floorSelectorPanel.goButtons[i].addActionListener(floorSelectorListener);
        }
        for (int i = 0; i < ElevatorUserInterface.floorNumber; i++)
        {
            userInterface.controlPanel.upButtons[i].addActionListener(controlPanelListener);
            userInterface.controlPanel.downButtons[i].addActionListener(controlPanelListener);
        }

        for (int i = 0; i < ElevatorUserInterface.elevatorNumber; i++)
        {
            Thread t = new Thread(controllers[i]);
            t.start();
        }

    }
    public static int getNearest(int thisFloor)
    {
        int tempDistance = 2 * ElevatorUserInterface.floorNumber;
        int minDistance = tempDistance;
        int nearest = -1;

        for (int i = 0; i < ElevatorUserInterface.elevatorNumber; i++)
        {
            tempDistance = getDistance(thisFloor, i);
            if (tempDistance < minDistance){
                minDistance = tempDistance;
                nearest = i;
            }
        }
        return nearest;
    }

    private static int getDistance(int thisFloor, int eleNum){

        int currentFloor = userInterface.elevatorSet[eleNum].getCurrentFloor();
        int currentDirection = userInterface.elevatorSet[eleNum].getDirection();

        if (thisFloor == currentFloor) return 0;
        if (thisFloor > currentFloor){
            if (!userInterface.elevatorSet[eleNum].isRunning()
                    || currentDirection == ElevatorUserInterface.ElevatorPanel.UP)
            {
                return thisFloor - currentFloor;
            }
            else {
                return thisFloor + currentFloor;
            }
        }
        else {
            if(!userInterface.elevatorSet[eleNum].isRunning()
                    || currentDirection == ElevatorUserInterface.ElevatorPanel.DOWN)
            {
                return currentDirection - thisFloor;
            }
            else {
                return 2 * ElevatorUserInterface.floorNumber - 2 - thisFloor - currentFloor;
            }
        }
    }


    public static ElevatorController getController(int i){
        if (i >= 0 && i < ElevatorUserInterface.elevatorNumber) return controllers[i];
        return null;
    }


    ArrayList<Integer> queue = new ArrayList<>();
    ElevatorUserInterface.ElevatorPanel elevator;
    private int [] PassengerSpreadsheet = new int[ElevatorUserInterface.floorNumber];


    public ElevatorController(ElevatorUserInterface.ElevatorPanel e)
    {
        this.elevator = e;
    }

    public void addToQueue(int targetFloor,int status){
        if (targetFloor < 0 || targetFloor >= ElevatorUserInterface.floorNumber) return;
        if(queue.contains(targetFloor)) return;

        queue.add(targetFloor);
//        queue.sort(Integer::compareTo);


        PassengerSpreadsheet[targetFloor] = status;


    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                RunCheck();
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


    private void RunCheck() throws InterruptedException
    {
        if (elevator.isRunning()){

            int currentFloor = elevator.getCurrentFloor();
            if (queue.contains(currentFloor)){
                queue.remove(queue.indexOf(currentFloor));

                if (PassengerSpreadsheet[currentFloor] == SRC){
                    PassengerSpreadsheet[currentFloor] = 0;
                    elevator.setCurrentLoad(elevator.getCurrentLoad() + 1);

                }
                else if (PassengerSpreadsheet[currentFloor]== DEST){
                    PassengerSpreadsheet[currentFloor] = 0;
                    elevator.setCurrentLoad(elevator.getCurrentLoad() - 1);
                }

                elevator.openDoor();

            }

            else {
                if(elevator.getDirection() == ElevatorUserInterface.ElevatorPanel.UP)
                    elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                else if(elevator.getDirection() == ElevatorUserInterface.ElevatorPanel.DOWN)
                    elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
            }
        }
        updateStatus();
    }

    public void updateStatus(){
        if(queue.isEmpty()) elevator.setRunning(false);
        else elevator.setRunning(true);

        if (elevator.getCurrentFloor() == 0) elevator.setDirection(ElevatorUserInterface.ElevatorPanel.UP);
        else if (elevator.getCurrentFloor() == ElevatorUserInterface.floorNumber - 1)
            elevator.setDirection(ElevatorUserInterface.ElevatorPanel.DOWN);
        else{
            if(elevator.getDirection() == ElevatorUserInterface.ElevatorPanel.UP &&
                    (!elevator.isRunning() || (elevator.getCurrentFloor() < queue.get(queue.size()-1)))){
                elevator.setDirection(ElevatorUserInterface.ElevatorPanel.UP);
            }
            else elevator.setDirection(ElevatorUserInterface.ElevatorPanel.DOWN);

            if(elevator.getDirection() == ElevatorUserInterface.ElevatorPanel.DOWN &&
                    (!elevator.isRunning() || (elevator.getCurrentFloor() > queue.get(0)))){
                elevator.setDirection(ElevatorUserInterface.ElevatorPanel.DOWN);
            }
            else elevator.setDirection(ElevatorUserInterface.ElevatorPanel.UP);
        }
    }
}


class ControlPanelListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton clicked = ((JButton) e.getSource());
        int targetFloor = Integer.parseInt(clicked.getName());
        ElevatorController controller = ElevatorController.getController(ElevatorController.getNearest(targetFloor));
        if(controller != null)
            controller.addToQueue(targetFloor,ElevatorController.SRC);
    }
}

class FloorSelectorListener implements ActionListener
{
    public static ElevatorUserInterface userInterface;
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton clicked = ((JButton) e.getSource());
        int targetElevator = Integer.parseInt(clicked.getName());
        int targetFloor = Integer.parseInt(
                userInterface.floorSelectorPanel.floorSelectors[targetElevator].getSelectedItem().toString()) - 1;

        ElevatorController controller = ElevatorController.getController(targetElevator);
        if (controller != null)
            controller.addToQueue(targetFloor, ElevatorController.DEST);
    }
}
