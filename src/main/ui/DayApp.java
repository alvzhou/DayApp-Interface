package ui;

import model.clothes.Clothes;
import model.clothes.Clothing;
import model.meals.Meals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import model.tasks.Task;
import model.tasks.TaskList;

import java.util.Scanner;

import persistence.JsonReader;
import persistence.JsonWriter;


//SOURCES: AccountNotRobust(TellerApp), JsonSerializationDemo

//EFFECTS: initializes Scanner and lists required for DayApp to function
public class DayApp {
    private static final String JSON_TaskSave = "./data/taskSave.json";
    private static final String JSON_MealSave = "./data/mealSave.json";
    private static final String JSON_OutfitSave = "./data/outfitSave.json";
    private Scanner scanner = new Scanner(System.in);

    TaskList listOfTasks = new TaskList("Alvin's List of Tasks");
    Meals listOfMeals = new Meals("Alvin's List of Meals");
    Clothes listOfOutfits = new Clothes("Alvin's List of Clothes");
    JsonReader jsonTaskReader = new JsonReader(JSON_TaskSave);
    JsonWriter jsonTaskWriter = new JsonWriter(JSON_TaskSave);
    JsonReader jsonMealReader = new JsonReader(JSON_MealSave);
    JsonWriter jsonMealWriter = new JsonWriter(JSON_MealSave);
    JsonReader jsonOutfitReader = new JsonReader(JSON_OutfitSave);
    JsonWriter jsonOutfitWriter = new JsonWriter(JSON_OutfitSave);


    String name;

    //EFFECTS: Initializes a DayMenu Console Interface in a loop, unless userInput quits Menu
    public DayApp() {
        boolean showMenuLoop = true;

        while (showMenuLoop) {
            displayDayMenu();
            String userInput = scanner.next();
            if (userInput.equals("Q")) {
                showMenuLoop = false;
            } else {
                playerInitialInput(userInput);
            }
        }

    }

    //EFFECTS: Displays the Main Menu of the Day Class, with an option to reset the day
    private void displayDayMenu() {
        System.out.println("DayApp Interface v0.2:");
        System.out.println("[T] = Tasks Menu, [M] = Meals Menu, [C] = Clothes Menu");
        System.out.println("[S] = Save Day");
        System.out.println("[L] = Load Day");
        System.out.println("[E] = Reset Day (Tasks, Meals, Clothes Reset)");
        System.out.println("[Q] = Quit Menu");
    }

    //EFFECTS: userInput of DayMenu
    private void playerInitialInput(String userInput) {
        if (userInput.equals("T")) {
            taskMenu();
        } else if (userInput.equals("M")) {
            mealsMenu();
        } else if (userInput.equals("C")) {
            clothingMenu();
        } else if (userInput.equals("E")) {
            clearEntireDay();
        } else if (userInput.equals("S")) {
            saveDay();
        } else if (userInput.equals("L")) {
            loadDay();
        } else {
            System.out.println("Wrong Key inputted. Please Try Again.");
        }

    }

    //EFFECTS: Displays a task menu to add, remove, view, or print tasks
    private void taskMenu() {
        Integer taskInput = 0;

        while (!(taskInput.equals(1) || taskInput.equals(2) || taskInput.equals(3) || taskInput.equals(4))) {
            System.out.println("Choose from the following selections below :");
            System.out.println("1. Add a task");
            System.out.println("2. Remove a task");
            System.out.println("3. View list of tasks");
            System.out.println("4. Print tasks remaining");
            taskInput = scanner.nextInt();
        }

        if (taskInput.equals(1)) {
            addTaskInDayApp();
        } else if (taskInput.equals(2)) {
            removeTaskInList();
        } else if (taskInput.equals(3)) {
            listNamesOfTasks();
        } else if (taskInput.equals(4)) {
            listNumberOfTasks();
        } else {
            System.out.println("Try Again");
        }
    }

    //MODIFIES: listOfTasks
    //EFFECTS: Allows UserInput to add a task in a list

    public void addTaskInDayApp() {
        System.out.println("Enter a Name for the Task:");
        name = scanner.nextLine();
        name = scanner.nextLine();
        Task taskOne = new Task(name);
        System.out.println("Enter the Description of the Task:");
        String description = scanner.nextLine();
        taskOne.setDescription(description);
        while (true) {
            System.out.println("Enter the Deadline of the Task (Enter in the form DD/MM/YYYY):");
            String deadline = scanner.nextLine();
            try {
                taskOne.setDeadline(deadline);
                break;
            } catch (ParseException e) {
                System.out.println("Wrong Date Implemented. Please try again.");
            }
        }
        System.out.println("Enter the Priority of the Task");
        String priority = scanner.next();
        taskOne.setPriority(priority);
        listOfTasks.addItem(taskOne);
        System.out.println("Success! Returning to Main Menu.");
    }

    //REQUIRES: List must contain at least one element
    //MODIFIES: listOfTasks
    //EFFECTS: Removes an added name from the TaskList, if wrong name entered, restarts again
    public void removeTaskInList() {
        String nameToRemove;
        Integer sizeOfList;
        Integer sizeOfListTwo;
        System.out.println("Please type in the name of the task that you would like to remove:");
        nameToRemove = scanner.nextLine();
        nameToRemove = scanner.nextLine();
        sizeOfList = listOfTasks.returnSizeOfItems();
        listOfTasks.removeItem(listOfTasks.returnItemName(nameToRemove));
        sizeOfListTwo = listOfTasks.returnSizeOfItems();
        while (!(sizeOfListTwo.equals(sizeOfList - 1))) {
            System.out.println("Wrong Task Name. Please Try Again");
            nameToRemove = scanner.nextLine();
            listOfTasks.removeItem(listOfTasks.returnItemName(nameToRemove));
            sizeOfListTwo = listOfTasks.returnSizeOfItems();
        }
        System.out.println("Task Crossed off! Returning to Main Menu.");
    }

    //EFFECTS: Displays a list of tasks by task name
    public void listNamesOfTasks() {
        List<String> listOfNames = listOfTasks.itemToNames();
        for (String s : listOfNames) {
            System.out.println(s);
        }
    }

    //EFFECTS: Display number of tasks, else produces 0
    public void listNumberOfTasks() {
        System.out.println(listOfTasks.returnSizeOfItems());
    }

    //EFFECTS: Displays a Meals Menu, with ability to add or remove a meal
    public void mealsMenu() {
        Integer mealsInput = 0;

        while (!(mealsInput.equals(1) || mealsInput.equals(2))) {
            System.out.println("Choose from the following selections below :");
            System.out.println("1. Enter the meals of the Day");
            System.out.println("2. Eat a Meal (Removes a Meal from the list)");
            mealsInput = scanner.nextInt();
        }

        if (mealsInput.equals(1)) {
            addMeals();
        } else if (mealsInput.equals(2)) {
            removeMeals();
        } else {
            System.out.println("Try Again");
        }
    }

    //MODIFIES: listOfMeals
    //EFFECTS: adds a meal to listOfMeals
    public void addMeals() {
        String description;
        String mealName;
        System.out.println("Enter the Name of the Meal");
        mealName = scanner.nextLine();
        mealName = scanner.nextLine();
        model.meals.Food mealOne = new model.meals.Food(mealName);
        System.out.println("Categorize Meal: (Breakfast, Lunch, Dinner, Snack)");
        description = scanner.nextLine();
        mealOne.setFoodDescription(description);
        listOfMeals.addItem(mealOne);
        System.out.println("Success! Returning to Main Menu.");
    }


    //REQUIRES: List must contain at least one element
    //MODIFIES: listOfMeals
    //EFFECTS: Removes an added name from the listOfMeals, if wrong name entered, restarts again
    public void removeMeals() {
        String mealsToEat;
        Integer sizeOfMeal;
        Integer sizeofMealTwo;
        System.out.println("Please type in the meal you had just ate:");
        mealsToEat = scanner.nextLine();
        mealsToEat = scanner.nextLine();
        sizeOfMeal = listOfMeals.returnSizeOfItems();
        listOfMeals.removeItem(listOfMeals.returnItemName(mealsToEat));
        sizeofMealTwo = listOfMeals.returnSizeOfItems();
        while (!(sizeofMealTwo.equals(1 - sizeOfMeal))) {
            System.out.println("Wrong Food Entered. Please Try Again");
            mealsToEat = scanner.nextLine();
            listOfMeals.removeItem(listOfMeals.returnItemName(mealsToEat));
            sizeofMealTwo = listOfMeals.returnSizeOfItems();
        }
        System.out.println("Food Eaten! Returning to Main Menu");
    }


    //EFFECTS: Display Clothing Menu
    public void clothingMenu() {
        Integer clothingInput = 0;

        while (!(clothingInput.equals(1) || clothingInput.equals(2))) {
            System.out.println("Choose from the following selections below :");
            System.out.println("1. Enter the outfit of the day");
            System.out.println("2. Remove an outfit");
            clothingInput = scanner.nextInt();
        }

        if (clothingInput.equals(1)) {
            addClothing();
        } else if (clothingInput.equals(2)) {
            removeOutfit();
        } else {
            System.out.println("Try Again");
        }
    }

    //MODIFIES: listOfOutfits
    //EFFECTS: adds clothing to listOfClothing
    public void addClothing() {
        String name;

        System.out.println("Enter a Name for the Outfit");
        name = scanner.nextLine();
        name = scanner.nextLine();
        Clothing clothingOne = new Clothing(name);
        System.out.println("Enter the top outfit you will be wearing");
        String top = scanner.nextLine();
        clothingOne.setTop(top);
        System.out.println("Enter the bottom outfit (pants) you will be wearing");
        String bottom = scanner.nextLine();
        clothingOne.setBottom(bottom);
        System.out.println("Enter what shoes you will be wearing");
        String footwear = scanner.next();
        clothingOne.setFootwear(footwear);
        scanner.nextLine();
        System.out.println("Finally, enter any other accessories");
        String accessories = scanner.nextLine();
        clothingOne.setAccessories(accessories);
        listOfOutfits.addItem(clothingOne);
        System.out.println("Success! Returning to Main Menu.");

    }

    //REQUIRES: List must contain at least one element
    //MODIFIES: listOfOutfits
    //EFFECTS: Removes an added name from the listOfOutfits, if wrong name entered, restarts again
    public void removeOutfit() {
        String outfitToRemove;
        Integer sizeOfOutfits;
        Integer sizeOfOutfitsTwo;
        System.out.println("Type in the name of the outfit you would like to remove");
        outfitToRemove = scanner.nextLine();
        outfitToRemove = scanner.nextLine();
        sizeOfOutfits = listOfOutfits.returnSizeOfItems();
        listOfOutfits.removeItem(listOfOutfits.returnItemName(outfitToRemove));
        sizeOfOutfitsTwo = listOfOutfits.returnSizeOfItems();
        while (!(sizeOfOutfits.equals(1 - sizeOfOutfitsTwo))) {
            System.out.println("Wrong Outfit Name Entered. Try Again.");
            outfitToRemove = scanner.nextLine();
            listOfOutfits.removeItem(listOfOutfits.returnItemName(outfitToRemove));
            sizeOfOutfitsTwo = listOfOutfits.returnSizeOfItems();
        }
        System.out.println("Outfit Removed! Returning to Main Menu");
    }

    //MODIFIES: listOfTasks, listOfMeals, listOfOutfits
    //EFFECTS: Clears all the elements in each of Tasks, Meals, and Outfits
    public void clearEntireDay() {
        listOfOutfits.clearItemList();
        listOfMeals.clearItemList();
        listOfTasks.clearItemList();
        System.out.println("Day Reset!");
    }

    // EFFECTS: saves the Day to file
    private void saveDay() {
        try {
            jsonTaskWriter.open();
            jsonTaskWriter.writeTaskList(listOfTasks);
            jsonTaskWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write TaskList to file: " + JSON_TaskSave);
        }
        try {
            jsonMealWriter.open();
            jsonMealWriter.writeMeals(listOfMeals);
            jsonMealWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write Meals to file: " + JSON_MealSave);
        }
        try {
            jsonOutfitWriter.open();
            jsonOutfitWriter.writeClothes(listOfOutfits);
            jsonOutfitWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write Outfits to file: " + JSON_MealSave);
        }
        System.out.println("Saved Day!");
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadDay() {
        try {
            listOfTasks = jsonTaskReader.readTaskList();
            System.out.println("Loaded TaskList from " + JSON_TaskSave);
        } catch (IOException e) {
            System.out.println("Unable to read listOfTasks from file: " + JSON_TaskSave);
        }

        try {
            listOfOutfits = jsonOutfitReader.readClothes();
            System.out.println("Loaded Clothes from " + JSON_OutfitSave);
        } catch (IOException e) {
            System.out.println("Unable to read listOfOutfits from file: " + JSON_OutfitSave);
        }

        try {
            listOfMeals = jsonMealReader.readMeals();
            System.out.println("Loaded Meals from " + JSON_MealSave);
        } catch (IOException e) {
            System.out.println("Unable to read listOfMeals from file: " + JSON_MealSave);
        }

    }


}




