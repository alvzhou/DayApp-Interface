package ui.dayui;

import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

//SOURCES: BroCode GUI SWING Tutorial,  BroCode Clock Tutorial
public class Gui extends JFrame implements ActionListener {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JFrame frame;
    private SimpleDateFormat dayFormat;
    private JLabel dayLabel;
    private String day;
    private ClothingWindow clothingMenu;
    private MealsMenu mealsMenu;
    private TaskWindow taskWindow;


    //EFFECTS: Constructs an instance of a GUI HomeScreen For DayApp
    public Gui() {
        initializeJFrame();
        initializeDayAndDate();
        initializeBackground();
        addButtons();
        frame.pack();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
            }
        }, 2 * 60 * 1000, 2 * 60 * 1000);

    }

    //EFFECTS: initializes JFrame
    public void initializeJFrame() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventLog();
                dispose();
            }
        });
    }

    public void printEventLog() {
        for (model.Event next : EventLog.getInstance()) {
            System.out.println(next);
        }
    }

    //EFFECTS: initialize Background
    public void initializeBackground() {
        JLabel label = new JLabel();
        day = dayFormat.format(Calendar.getInstance().getTime());
        dayLabel.setText(day);
        label.setText("Hello Alvin. Today is: " + day);
        label.setForeground(Color.white);
        label.setFont(new Font("MV Boli", Font.PLAIN, 80));
        label.setHorizontalTextPosition(JLabel.CENTER);
        ImageIcon background = new ImageIcon("src/main/ui/images/windows.png");
        label.setIcon(background);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        JPanel frontCover = new JPanel();
        frontCover.add(label);
        frame.add(frontCover, BorderLayout.NORTH);
    }

    //EFFECTS: add buttons to JFrame
    public void addButtons() {
        JPanel buttonPanel = new JPanel();
        b1 = new JButton("TaskMenu");
        b1.setPreferredSize(new Dimension(300, 100));
        b1.addActionListener(this);
        b2 = new JButton("ClothingMenu");
        b2.setPreferredSize(new Dimension(300, 100));
        b2.addActionListener(this);
        b3 = new JButton("MealMenu");
        b3.setPreferredSize(new Dimension(300, 100));
        b3.addActionListener(this);
        b4 = new JButton("Clear All Entities");
        b4.setPreferredSize(new Dimension(300, 100));
        b4.addActionListener(this);
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    //EFFECTS: User Input for MealsMenu, TaskWindow, ClothingWindow, and to clear data
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            taskWindow = new TaskWindow();
        } else if (e.getSource() == b3) {
            mealsMenu = new MealsMenu();
        } else if (e.getSource() == b2) {
            clothingMenu = new ClothingWindow();
        } else if (e.getSource() == b4) {
            taskWindow = new TaskWindow();
            taskWindow.destroyTaskWindow();
            mealsMenu = new MealsMenu();
            mealsMenu.destroyMealWindow();
            clothingMenu = new ClothingWindow();
            clothingMenu.destroyClothingWindow();
        }
    }


    //EFFECTS initializes Day and Date of Jframe
    public void initializeDayAndDate() {
        dayFormat = new SimpleDateFormat("EEEE");

        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Serif Bold", Font.PLAIN, 35));
        dayLabel.setVisible(true);

    }


}


