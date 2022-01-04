package model;

import model.tasks.Task;
import model.tasks.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
private TaskList tasksTest;
private List<String> stringListTest;

    @BeforeEach
    public void setup() {
        tasksTest = new TaskList("Alvin's Test Tasks");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, tasksTest.returnSizeOfItems());
    }

    @Test
    public void testAddTasks() {
        Task taskOne = new Task("Task One");
        tasksTest.addItem(taskOne);
        assertEquals(1, tasksTest.returnSizeOfItems());
    }

    @Test
    public void testAddTwoTasksAndCheckFieldsWithin() {
        Date DeadlineDate;
        Task taskOne = new Task("Dog Walk");
        Task taskTwo = new Task("Fishing");
        tasksTest.addItem(taskOne);
        tasksTest.addItem(taskTwo);
        assertEquals(2, tasksTest.returnSizeOfItems());
        taskOne.setDescription("Walking a Dog");
        assertEquals("Walking a Dog", taskOne.getDescription());
        try {
            taskOne.setDeadline("10/10/2021");
        } catch (ParseException e) {
            System.out.println("Try Again");;
        }
        DeadlineDate = taskOne.getDeadline();
        assertEquals(DeadlineDate, taskOne.getDeadline());
        taskOne.setPriority("Low");
        assertEquals("Low", taskOne.getPriority());
    }

    @Test
    public void testRemoveTasks() {
        Task taskOne = new Task("Walking a Dog");
        tasksTest.removeItem(taskOne);
        assertEquals(0, tasksTest.returnSizeOfItems());
    }

    @Test
    public void testRemoveTwoTasks() {
        Task taskOne = new Task("Walking a Dog");
        Task taskTwo = new Task("Fishing");
        tasksTest.addItem(taskOne);
        tasksTest.addItem(taskTwo);
        assertEquals(2, tasksTest.returnSizeOfItems());
        tasksTest.removeItem(taskOne);
        assertEquals(1, tasksTest.returnSizeOfItems());
        tasksTest.removeItem(taskTwo);
        assertEquals(0, tasksTest.returnSizeOfItems());
    }

    @Test
    public void testReturnTestBasedOnName() {
        Task taskOne = new Task("Walking a Dog");
        Task taskTwo = new Task("Fishing");
        tasksTest.addItem(taskOne);
        tasksTest.addItem(taskTwo);
        assertEquals(taskOne, tasksTest.returnItemName("Walking a Dog"));
        assertEquals(taskTwo, tasksTest.returnItemName("Fishing"));
        assertEquals(null, tasksTest.returnItemName("Homework"));
    }

    @Test
    public void testListOfStrings() {
        List<String> listOfStrings;
        Task taskOne = new Task("Walking a Dog");
        Task taskTwo = new Task("Fishing");
        tasksTest.addItem(taskOne);
        tasksTest.addItem(taskTwo);
        listOfStrings = tasksTest.itemToNames();
        assertEquals(listOfStrings.get(0), "Walking a Dog");
        assertEquals(listOfStrings.get(1), "Fishing");
    }

@Test
    public void testClearTaskList() {
    Task taskOne = new Task("Walking a Dog");
    Task taskTwo = new Task("Fishing");
    tasksTest.addItem(taskOne);
    tasksTest.addItem(taskTwo);
    tasksTest.clearItemList();
    assertEquals(0, tasksTest.returnSizeOfItems());
}

}
