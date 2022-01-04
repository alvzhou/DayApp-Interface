package ui.dayui;

import model.tasks.Task;
import model.tasks.TaskList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

//SOURCES: BroCode GUI SWING Tutorial
public class TaskWindow implements ActionListener {
    private JFrame taskMenu;
    private JLabel name;
    private JLabel desc;
    private JLabel deadline;
    private JLabel priority;
    private JLabel title;
    private JButton submit;
    private JButton save;
    private JTextField fieldOne;
    private JTextField fieldTwo;
    private JTextField fieldThree;
    private JTextField fieldFour;
    private JPanel topPanel;
    private JPanel titlePanel;
    private TaskList tasksGuiList;
    private DefaultListModel listModelTasks;
    private JList taskList;

    private static final String JSON_TaskSave = "./data/taskSave.json";
    JsonWriter jsonTaskWriter = new JsonWriter(JSON_TaskSave);
    JsonReader jsonTaskReader = new JsonReader(JSON_TaskSave);
    private JPanel buttonPanel;
    private JButton load;


    //EFFECTS: Constructs an instance of the Task Window GUI
    public TaskWindow() {
        initFrame();
        initJFields();
        initLabels();
        formTopPanel();
        formButtonPanel();

        GridLayout layout = new GridLayout();
        layout.setRows(4);
        layout.setColumns(1);
        taskMenu.setLayout(layout);
        taskMenu.add(titlePanel);
        taskMenu.add(taskList);
        taskMenu.add(topPanel);
        taskMenu.add(buttonPanel);



    }

    //EFFECTS: initializes JFrame, JList, DefaultListModel, and ArrayList of TaskList to be used
    public void initFrame() {
        taskMenu = new JFrame();
        taskMenu.setSize(600, 1200);
        taskMenu.setVisible(true);

        listModelTasks = new DefaultListModel<>();
        taskList = new JList(listModelTasks);
        tasksGuiList = new TaskList("Tasks in DayApp");
    }


    private void initJFields() {
        //Top Panel
        fieldOne = new JTextField(20);
        fieldTwo = new JTextField(20);
        fieldThree = new JTextField(20);
        fieldFour = new JTextField(20);

    }


    //EFFECTS: Initializes JLabels for TaskWindow
    private void initLabels() {
        title = new JLabel("Task Menu");
        title.setFont(new Font("MV Boli", Font.BOLD, 50));
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.TOP);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/ui/images/checklist.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(dimg);
        title.setIcon(background);
        titlePanel = new JPanel();
        titlePanel.add(title);

        name = new JLabel("Enter the name of task");
        desc = new JLabel("Enter the desc of task");
        deadline = new JLabel("Enter Deadline (DD/MM/YYYY)");
        priority = new JLabel("Enter the priority of task");
        initJButton();
    }

    //EFFECTS: Initializes Buttons for TaskWindow
    private void initJButton() {
        submit = new JButton("Submit");
        save = new JButton("Save Tasks");
        load = new JButton("Load Tasks");
        submit.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
    }

    //EFFECTS: initializes a JPanel for JTextFields
    private void formTopPanel() {
        topPanel = new JPanel();
        GridLayout topPanelLayout = new GridLayout();
        topPanelLayout.setRows(4);
        topPanelLayout.setColumns(2);
        topPanel.setLayout(topPanelLayout);
        topPanel.add(name);
        topPanel.add(fieldOne);
        topPanel.add(desc);
        topPanel.add(fieldTwo);
        topPanel.add(deadline);
        topPanel.add(fieldThree);
        topPanel.add(priority);
        topPanel.add(fieldFour);

    }

    //EFFECTS: initializes ButtonPanel
    private void formButtonPanel() {
        buttonPanel = new JPanel();
        GridLayout buttonLayout = new GridLayout();
        buttonLayout.setRows(1);
        buttonLayout.setRows(3);
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.add(submit);
        buttonPanel.add(save);
        buttonPanel.add(load);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            addTasksMenu();
        } else if (e.getActionCommand().equals("Save Tasks")) {
            saveToList();
        } else if (e.getActionCommand().equals("Load Tasks")) {
            loadTaskList();
        }

    }


    //MODIFIES: this
    //EFFECTS: adds Task to tasksGuiList
    private void addTasksMenu() {
        Task taskGUI = new Task(fieldOne.getText());
        taskGUI.setDescription(fieldTwo.getText());
        try {
            taskGUI.setDeadline(fieldThree.getText());
        } catch (ParseException e) {
            System.out.println("Wrong Date Implemented. Please try again.");
        }
        taskGUI.setPriority(fieldFour.getText());
        tasksGuiList.addItem(taskGUI);
        displayLoadedTaskInJList();

    }

    //EFFECTS: displays tasksGuiList in a DefaultListModel within a JList
    private void displayLoadedTaskInJList() {
        listModelTasks.clear();
        List<String> taskString = tasksGuiList.toStringTask();
        for (String next: taskString) {
            listModelTasks.addElement(next);
        }
    }

    //EFFECTS: saves tasksGuiList to jsonMealWriter
    public void saveToList() {
        try {
            jsonTaskWriter.open();
            jsonTaskWriter.writeTaskList(tasksGuiList);
            jsonTaskWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write Tasks to file: " + tasksGuiList);
        }
    }

    //EFFECTS: loads tasksGuiList from jsonMealReader
    public void loadTaskList() {
        try {
            tasksGuiList = jsonTaskReader.readTaskList();
            System.out.println("Loaded TaskList from " + JSON_TaskSave);
            displayLoadedTaskInJList();
        } catch (IOException e) {
            System.out.println("Unable to read listOfTasks from file: " + JSON_TaskSave);
        }
    }

    //EFFECTS: removes Task Window
    public void destroyTaskWindow() {
        taskMenu.setVisible(false);
        taskMenu.dispose();
        clearList();
    }


    //EFFECTS: clears list of tasksGuiList
    public void clearList() {
        tasksGuiList.clearItemList();
    }
}





