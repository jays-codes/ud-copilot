package jayslabs.copilot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
    }
    @Test
    public void testRemoveTask() {
        Task task1 = new Task("Test Task 1");
        Task task2 = new Task("Test Task 2");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        assertTrue(taskManager.removeTask("Test Task 1"));
        List<Task> tasks = taskManager.listTasks();
        assertEquals(1, tasks.size());
        assertEquals("Test Task 2", tasks.get(0).getDescription());
    }

    @Test
    public void testRemoveNonExistentTask() {
        assertFalse(taskManager.removeTask("Non-existent Task"));
    }

    @Test
    public void testAddTaskConcurrency() throws InterruptedException {
        Task task = new Task("Test Task 1");

        // Create multiple threads to add the same task simultaneously
        Thread thread1 = new Thread(() -> {
            assertTrue(taskManager.addTask(task));
        });
        Thread thread2 = new Thread(() -> {
            assertTrue(taskManager.addTask(task));
        });

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for the threads to finish
        thread1.join();
        thread2.join();

        List<Task> tasks = taskManager.listTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    public void testMarkTaskAsDoneNonExistentTask() {
        assertFalse(taskManager.markTaskAsDone("Non-existent Task"));
    }
    
    @Test
    public void testAddTask() {
        Task task = new Task("Test Task 1");
        assertTrue(taskManager.addTask(task));
        List<Task> tasks = taskManager.listTasks();
        assertEquals(1, tasks.size());
        assertEquals("Test Task 1", tasks.get(0).getDescription());
    }

    @Test
    public void testAddDuplicateTask() {
        Task task1 = new Task("Test Task 1");
        Task task2 = new Task("Test Task 1");
        assertTrue(taskManager.addTask(task1));
        assertFalse(taskManager.addTask(task2));
        List<Task> tasks = taskManager.listTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    public void testListTasks() {
        Task task1 = new Task("Test Task 1");
        Task task2 = new Task("Test Task 2");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        List<Task> tasks = taskManager.listTasks();
        assertEquals(2, tasks.size());
        assertEquals("Test Task 1", tasks.get(0).getDescription());
        assertEquals("Test Task 2", tasks.get(1).getDescription());
    }

    @Test
    public void testMarkTaskAsDone() {
        Task task = new Task("Test Task 1");
        taskManager.addTask(task);
        assertTrue(taskManager.markTaskAsDone("Test Task 1"));
        List<Task> tasks = taskManager.listTasks();
        assertTrue(tasks.get(0).isDone());
    }

    @Test
    public void testMarkNonExistentTaskAsDone() {
        assertFalse(taskManager.markTaskAsDone("Non-existent Task"));
    }
}