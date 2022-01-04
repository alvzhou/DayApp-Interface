package model;

import model.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testConstructor() {
        Task task = new Task("Homework");
        assertEquals("Homework", task.getName());
        assertEquals("", task.getDescription());
        assertEquals(null, task.getDeadline());
        assertEquals("", task.getPriority());
    }
}
