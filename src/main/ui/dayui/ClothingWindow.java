package ui.dayui;

import model.clothes.Clothes;
import model.clothes.Clothing;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

//SOURCES: BroCode GUI SWING Tutorial
public class ClothingWindow implements ActionListener {
    private JFrame clothingMenu;
    private JLabel name;
    private JLabel top;
    private JLabel bottom;
    private JLabel shoes;
    private JLabel accessories;
    private JLabel title;
    private JButton submit;
    private JButton save;
    private JButton load;
    private JTextField fieldOne;
    private JTextField fieldTwo;
    private JTextField fieldThree;
    private JTextField fieldFour;
    private JTextField fieldFive;
    private JPanel titlePanel;
    private JPanel topPanel;
    private Clothes clothesGuiList;
    private DefaultListModel listModelClothes;
    private JList clothingList;
    private static final String JSON_OutfitSave = "./data/outfitSave.json";
    JsonWriter jsonOutfitWriter = new JsonWriter(JSON_OutfitSave);
    JsonReader jsonOutfitReader = new JsonReader(JSON_OutfitSave);
    private JPanel buttonPanel;


    //EFFECTS: Constructs an instance of the ClothingWindow GUI
    public ClothingWindow() {
        initFrame();
        initJFields();
        initLabels();
        formTopPanel();

        GridLayout layout = new GridLayout();
        layout.setRows(4);
        layout.setColumns(1);
        clothingMenu.setLayout(layout);
        clothingMenu.add(titlePanel);
        clothingMenu.add(clothingList);
        clothingMenu.add(topPanel);
        clothingMenu.add(buttonPanel);

    }


    private void initJFields() {
        //Top Panel
        fieldOne = new JTextField(20);
        fieldTwo = new JTextField(20);
        fieldThree = new JTextField(20);
        fieldFour = new JTextField(20);
        fieldFive = new JTextField(20);

    }


    //EFFECTS: initializes JFrame, JList, DefaultListModel, and ArrayList of Clothes to be used
    public void initFrame() {
        clothingMenu = new JFrame();
        clothingMenu.setSize(500, 900);
        clothingMenu.setVisible(true);
        clothingMenu.setResizable(false);

        listModelClothes = new DefaultListModel<>();
        clothingList = new JList(listModelClothes);
        clothesGuiList = new Clothes("Clothes in DayApp");
    }

    //EFFECTS: Initializes JLabels for ClothingWindow
    private void initLabels() {
        title = new JLabel("Clothing Menu");
        title.setFont(new Font("MV Boli", Font.BOLD, 50));
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.TOP);
        ImageIcon background = getClothingImage();
        title.setIcon(background);
        titlePanel = new JPanel();
        titlePanel.add(title);

        name = new JLabel("Enter the name of outfit");
        top = new JLabel("Enter the top of outfit");
        bottom = new JLabel("Enter the bottom of outfit");
        shoes = new JLabel("Enter the shoes of outfit");
        accessories = new JLabel("Enter the accessories of outfit");
        buttonPanelInit();

    }

    //EFFECTS: Initializes Buttons for ClothingWindow
    private void buttonPanelInit() {
        buttonPanel = new JPanel();
        GridLayout buttonLayout = new GridLayout();
        buttonLayout.setRows(1);
        buttonLayout.setColumns(3);
        buttonPanel.setLayout(buttonLayout);
        submit = new JButton("Submit");
        save = new JButton("Save Clothing");
        load = new JButton("Load Clothing");
        submit.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        buttonPanel.add(submit);
        buttonPanel.add(save);
        buttonPanel.add(load);

    }

    //EFFECTS: retrieves clothing image for background
    private ImageIcon getClothingImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/ui/images/supremehoodie.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(dimg);
        return background;
    }

    //EFFECTS: initializes the panel for JText fields
    private void formTopPanel() {
        topPanel = new JPanel();
        GridLayout layoutTopPanel = new GridLayout();
        layoutTopPanel.setColumns(2);
        layoutTopPanel.setRows(5);
        topPanel.setLayout(layoutTopPanel);
        topPanel.add(name);
        topPanel.add(fieldOne);
        topPanel.add(top);
        topPanel.add(fieldTwo);
        topPanel.add(bottom);
        topPanel.add(fieldThree);
        topPanel.add(shoes);
        topPanel.add(fieldFour);
        topPanel.add(accessories);
        topPanel.add(fieldFive);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            addToClothingList();
        } else if (e.getActionCommand().equals("Save Clothing")) {
            saveToList();
        } else if (e.getActionCommand().equals("Load Clothing")) {
            loadClothingList();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds Clothing to clothesGuiList
    private void addToClothingList() {
        Clothing clothing = new Clothing(fieldOne.getText());
        clothing.setTop(fieldTwo.getText());
        clothing.setBottom(fieldThree.getText());
        clothing.setFootwear(fieldFour.getText());
        clothing.setAccessories(fieldFive.getText());
        clothesGuiList.addItem(clothing);
        displayLoadedClothingInJList();
    }


    //EFFECTS: displays clothesGuiList in a DefaultListModel within a JList
    private void displayLoadedClothingInJList() {
        listModelClothes.clear();
        List<String> attempt = clothesGuiList.toStringClothing();
        for (String next: attempt) {
            listModelClothes.addElement(next);
        }
    }



    //EFFECTS: saves clothesGuiList to jsonOutfitWriter
    public void saveToList() {
        try {
            jsonOutfitWriter.open();
            jsonOutfitWriter.writeClothes(clothesGuiList);
            jsonOutfitWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write Outfits to file: " + clothesGuiList);
        }
    }

    //EFFECTS: loads clothesGuiList from jsonOutfitReader
    public void loadClothingList() {
        try {
            clothesGuiList = jsonOutfitReader.readClothes();
            System.out.println("Loaded Clothes from " + JSON_OutfitSave);
            displayLoadedClothingInJList();
        } catch (IOException e) {
            System.out.println("Unable to read listOfOutfits from file: " + JSON_OutfitSave);
        }
    }

    //EFFECTS: removes Clothing Window
    public void destroyClothingWindow() {
        clothingMenu.setVisible(false);
        clearList();
        clothingMenu.dispose();

    }

    //EFFECTS: clears list of clothesGuiList
    public void clearList() {
        clothesGuiList.clearItemList();
    }



}
