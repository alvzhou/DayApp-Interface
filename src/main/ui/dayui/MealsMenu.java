package ui.dayui;

import model.meals.Food;
import model.meals.Meals;
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
import java.util.List;

//SOURCES: BroCode GUI SWING Tutorial
public class MealsMenu implements ActionListener {
    private JFrame mealMenu;
    private JLabel name;
    private JLabel description;
    private JLabel title;
    private JButton submit;
    private JButton save;
    private JButton load;
    private JTextField fieldOne;
    private JTextField fieldTwo;
    private JPanel titlePanel;
    private JPanel topPanel;
    private JPanel buttonPanel;
    private Meals mealsGuiList;
    private JList mealList;
    private DefaultListModel<String> listModelMeals;
    private static final String JSON_MealSave = "./data/mealSave.json";
    JsonWriter jsonMealWriter = new JsonWriter(JSON_MealSave);
    JsonReader jsonMealReader = new JsonReader(JSON_MealSave);

    //EFFECTS: Constructs an instance of the Meals Menu GUI
    public MealsMenu() {
        initFrame();
        initJFields();
        initLabelsAndJButtons();
        formTopPanel();
        initButtonPanel();

        GridLayout layout = new GridLayout();
        layout.setRows(4);
        layout.setColumns(1);
        mealMenu.setLayout(layout);
        mealMenu.add(titlePanel);
        mealMenu.add(mealList);
        mealMenu.add(topPanel);
        mealMenu.add(buttonPanel);

    }


    private void initJFields() {
        //Top Panel
        fieldOne = new JTextField(20);
        fieldTwo = new JTextField(20);
    }


    //EFFECTS: initializes JFrame, JList, DefaultListModel, and ArrayList of Meals to be used
    public void initFrame() {
        mealMenu = new JFrame();
        mealMenu.setSize(600, 1100);
        mealMenu.setVisible(true);

        //jpanels
        listModelMeals = new DefaultListModel<>();
        mealList = new JList(listModelMeals);
        mealsGuiList = new Meals("Meals in DayApp");
    }

    //EFFECTS: Initializes JLabels and Buttons for MealsMenu
    private void initLabelsAndJButtons() {
        initTitlePanel();
        name = new JLabel("Enter the name of meal");
        description = new JLabel("Enter the description of meal");
        submit = new JButton("Submit");
        save = new JButton("Save Meals");
        load = new JButton("Load Meals");
        submit.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
    }

    //EFFECTS: initializes title and image for MealsMenu
    private void initTitlePanel() {
        title = new JLabel("Meals Menu");
        GridLayout layoutTitle = new GridLayout();
        layoutTitle.setRows(2);
        layoutTitle.setColumns(1);
        title.setLayout(layoutTitle);
        title.setFont(new Font("MV Boli", Font.BOLD, 50));
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.TOP);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/ui/images/java.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(200, 160, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(dimg);
        title.setIcon(background);
        titlePanel = new JPanel();
        titlePanel.add(title);
        titlePanel.add(mealList);
    }


    //EFFECTS: initializes the panel for JText fields
    private void formTopPanel() {
        topPanel = new JPanel();
        GridLayout layoutTopPanel = new GridLayout();
        layoutTopPanel.setRows(2);
        layoutTopPanel.setColumns(2);
        topPanel.setLayout(layoutTopPanel);
        topPanel.add(name);
        topPanel.add(fieldOne);
        topPanel.add(description);
        topPanel.add(fieldTwo);

    }

    //EFFECTS: initializes ButtonPanel
    private void initButtonPanel() {
        buttonPanel = new JPanel();
        GridLayout buttonLayout = new GridLayout();
        buttonLayout.setColumns(3);
        buttonLayout.setRows(1);
        buttonPanel.setLayout(buttonLayout);
        submit.setSize(200, 100);
        save.setSize(200, 100);
        submit.setSize(200, 100);
        buttonPanel.add(submit);
        buttonPanel.add(save);
        buttonPanel.add(load);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            addMealsMenu();
        } else if (e.getActionCommand().equals("Save Meals")) {
            saveToList();
        } else if (e.getActionCommand().equals("Load Meals")) {
            loadMealList();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds Meal to mealsGuiList
    private void addMealsMenu() {
        Food mealGUI = new Food(fieldOne.getText());
        mealGUI.setFoodDescription(fieldTwo.getText());
        mealsGuiList.addItem(mealGUI);
        displayLoadedMealInJList();
    }

    //EFFECTS: displays mealsGuiList in a DefaultListModel within a JList
    private void displayLoadedMealInJList() {
        listModelMeals.clear();
        List<String> attempt = mealsGuiList.toStringLoadedMeals();
        for (String next : attempt) {
            listModelMeals.addElement(next);
        }
    }

    //EFFECTS: saves mealsGuiList to jsonMealWriter
    public void saveToList() {
        try {
            jsonMealWriter.open();
            jsonMealWriter.writeMeals(mealsGuiList);
            jsonMealWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write Outfits to file: " + mealsGuiList);
        }
    }

    //EFFECTS: loads mealsGuiList from jsonMealReader
    public void loadMealList() {
        try {
            mealsGuiList = jsonMealReader.readMeals();
            System.out.println("Loaded Meals from " + JSON_MealSave);
            displayLoadedMealInJList();
        } catch (IOException e) {
            System.out.println("Unable to read listOfMeals from file: " + JSON_MealSave);
        }
    }

    //EFFECTS: removes Meals Window
    public void destroyMealWindow() {
        mealMenu.setVisible(false);
        mealMenu.dispose();
        clearList();
    }

    //EFFECTS: clears list of mealsGuiList
    public void clearList() {
        mealsGuiList.clearItemList();
    }
}
