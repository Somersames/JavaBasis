package Thread.elvgithub;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by szh on 2017/5/11.
 */
public class ElevatorUserInterface extends JFrame{
    public static final int elevatorNumber = 5;
    public static final int floorNumber = 20;

    ControlPanel controlPanel = new ControlPanel();
    ElevatorPanel[] elevatorSet = new ElevatorPanel[elevatorNumber];
    FloorSelectorPanel floorSelectorPanel = new FloorSelectorPanel();
    InfoPanel infoPanel = new InfoPanel();


    public ElevatorUserInterface()
    {
        for (int i = 0; i < elevatorNumber; i++)
        {
            elevatorSet[i] = new ElevatorPanel();
        }


        setTitle("ELEVATOR SIMULATOR");

        GridBagLayout mainLayout = new GridBagLayout();
        setLayout(mainLayout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets.bottom = 3;
        constraints.insets.top = 3;


        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 9;
        constraints.gridheight = 20;
        add(controlPanel, constraints);

        constraints.gridx = 9;
        constraints.gridy = 4;
        constraints.gridwidth = 75;
        constraints.gridheight = 3;
        add(floorSelectorPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 27;
        constraints.gridwidth = 81;
        constraints.gridheight = 7;
        add(infoPanel, constraints);


        constraints.gridy = 7;
        constraints.gridwidth = 15;
        constraints.gridheight = 20;
        constraints.weightx = 0.7;
        constraints.anchor = GridBagConstraints.CENTER;
        for (int i = 0; i < elevatorNumber; i++)
        {
            constraints.gridx = 9 + 15 * i;
            add(elevatorSet[i], constraints);
        }

        pack();
        Dimension d = getContentPane().getSize();
        Dimension newDimension = new Dimension(((int) (d.width * 1.06)), ((int) (d.height * 1.02)));
        setSize(newDimension);
        setMinimumSize(newDimension);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    class FloorSelectorPanel extends JPanel
    {
        /**
         * The FloorSelectorPanel contains appropriate number of ComboBox
         * that specifies the target floor one tends to go to.
         */
        JComboBox[] floorSelectors = new JComboBox[elevatorNumber];
        JButton[] goButtons = new JButton[elevatorNumber];

        Vector<String> vector = new Vector<>();

        public FloorSelectorPanel()
        {

            setLayout(new GridLayout(1, 2 * elevatorNumber));

            for (int i = 0; i < floorNumber; i++)
            {
                vector.add(i, "" + (i + 1));
            }
            for (int i = 0; i < elevatorNumber; i++)
            {
                floorSelectors[i] = new JComboBox<>(vector);
                goButtons[i] = new JButton("GO");
                goButtons[i].setName(""+i);

                add(floorSelectors[i]);
                add(goButtons[i]);

            }
        }
    }


    class ControlPanel extends JPanel
    {
        /**
         * The ControlPanel contains 20 upButtons and 20 downButtons respectively.
         * Up and Down buttons are for requesting an available elevator
         * Pressing a button triggers a new thread to be created
         */
        public JLabel[] floorSigns = new JLabel[floorNumber];
        public JButton[] upButtons = new JButton[floorNumber];
        public JButton[] downButtons = new JButton[floorNumber];

        public ControlPanel()
        {
            /**
             * The constructor of class ControlPanel
             * The buttons represent the actual buttons that receive signals indicating the a request of elevator
             */
            setLayout(new GridLayout(floorNumber, 3));

            for (int i = floorNumber - 1; i >= 0; i--)
            {
                floorSigns[i] = new JLabel("" + (1 + i),SwingConstants.CENTER);
                upButtons[i] = new JButton("⬆︎");
                downButtons[i] = new JButton("⬇︎");

                upButtons[i].setName(""+i);
                downButtons[i].setName(""+i);

                add(upButtons[i]);
                add(downButtons[i]);
                add(floorSigns[i]);



            }

            upButtons[19].setVisible(false);
            downButtons[0].setVisible(false);
        }
    }


    class ElevatorPanel extends JPanel
    {
        /**
         * Elevator instance represents a actual elevator space ranging from the ground floor to the highest floor
         * Each label represents a specific floor
         * Instance variable currentFloor indicates the floor the elevator is now on
         * Instance variable targetFloor is of meaning when in running status
         * The floor labels are marked black by default, the floor where the elevator exists is marked blue, orange, and red
         * depending on specified status
         */

        public static final int UP = 1;
        public static final int IDLE = 0;
        public static final int DOWN = -1;

        private boolean running = false;
        private int direction = UP;
        private int currentFloor = 0;
        private int currentLoad = 0;

        public int getCurrentLoad()
        {
            return currentLoad;
        }

        public void setCurrentLoad(int currentLoad)
        {
            if (currentLoad <= 0) this.currentLoad = 0;
            else this.currentLoad = currentLoad;
        }

        public int getCurrentFloor()
        {
            return currentFloor;
        }

        public void setCurrentFloor(int currentFloor)
        {
            elevator[this.currentFloor].setBackground(Color.gray);
            this.currentFloor = currentFloor;
            elevator[this.currentFloor].setBackground(getCurrentColor());

        }

        public boolean isRunning()
        {
            return running;
        }

        public void setRunning(boolean running)
        {
            this.running = running;
        }

        public int getDirection()
        {
            return direction;
        }

        public void setDirection(int direction)
        {
            this.direction = direction;
        }


        public Color getCurrentColor(){
            if (getCurrentLoad() > 0) return Color.orange;

            return Color.yellow;
        }

        public void openDoor() throws InterruptedException
        {
            for (int i = 0; i < 3; i++)
            {
                elevator[currentFloor].setBackground(Color.red);
                Thread.sleep(250);
                elevator[currentFloor].setBackground(getCurrentColor());
            }
        }

        JPanel[] elevator = new JPanel[floorNumber];



        public ElevatorPanel()
        {
            /**
             * The constructor of class elevator, each instance representing an elevator.
             * The labels represent each floor.
             */

            setLayout(new GridLayout(floorNumber,1));
            for (int i = floorNumber - 1; i >= 0; i--)
            {
                elevator[i] = new JPanel();
                elevator[i].setBackground(Color.gray);
                add(elevator[i]);
            }
            setCurrentFloor(0);
        }
    }

    class InfoPanel extends Panel
    {
        /**
         * The information panel shows necessary specification of the interface
         * Explains different colors of the floor label
         * Author information Included
         */
        public InfoPanel()
        {
            JLabel label1 = new JLabel("Created by: Mingjie Ma, No:1452735, All Rights Reserved");
            JLabel label2 = new JLabel("Notes: ");
            JLabel label3 = new JLabel("Red Lights On: Door Opening");
            JLabel label4 = new JLabel("Orange Lights On: Elevator Loaded");
            JLabel label5 = new JLabel("Yellow Lights On: Elevator Empty");

            setLayout(new GridLayout(5, 1));
            Font f = new Font(Font.SERIF, Font.ITALIC, 10);

            label1.setFont(f);
            label2.setFont(f);
            label3.setFont(f);
            label4.setFont(f);
            label5.setFont(f);

            add(label2);
            add(label3);
            add(label4);
            add(label5);
            add(label1);
        }
    }
}
