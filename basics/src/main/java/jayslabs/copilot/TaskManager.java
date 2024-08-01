package jayslabs.copilot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

class Task {
	private String description;
	private boolean done;

	public Task(String description) {
		this.description = Objects.requireNonNull(description, "Description cannot be null");
		this.done = false;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDone() {
		return done;
	}

	public void markAsDone() {
		this.done = true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return description.equals(task.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}
}

/**
 * The TaskManager class represents a manager for tasks.
 * It provides methods to add tasks, list tasks, and mark tasks as done.
 */
public class TaskManager {
	private static final Logger logger = Logger.getLogger(TaskManager.class.getName());
	private List<Task> tasks;

	public TaskManager() {
		this.tasks = Collections.synchronizedList(new ArrayList<>());
	}

	/**
	 * Adds a task to the task manager.
	 *
	 * @param task the task to be added
	 * @return true if the task was successfully added, false if a task with the same description already exists
	 */
	public synchronized boolean addTask(Task task) {
		Objects.requireNonNull(task, "Task cannot be null");
		if (findTaskByDescription(task.getDescription()) != null) {
			logger.warning("Task with description '" + task.getDescription() + "' already exists.");
			return false; // Task with the same description already exists
		}
		tasks.add(task);
		logger.info("Task added: " + task.getDescription());
		return true;
	}

	/**
	 * Returns an unmodifiable list of all tasks in the task manager.
	 *
	 * @return the list of tasks
	 */
	public List<Task> listTasks() {
		synchronized (tasks) {
			return Collections.unmodifiableList(new ArrayList<>(tasks));
		}
	}

	/**
	 * Marks a task as done based on its description.
	 *
	 * @param description the description of the task to be marked as done
	 * @return true if the task was found and marked as done, false if the task was not found
	 */
	public synchronized boolean markTaskAsDone(String description) {
		Task task = findTaskByDescription(description);
		if (task != null) {
			task.markAsDone();
			logger.info("Task marked as done: " + description);
			return true;
		}
		logger.warning("Task with description '" + description + "' not found.");
		return false; // Task not found
	}

	private Task findTaskByDescription(String description) {
		for (Task task : tasks) {
			if (task.getDescription().equals(description)) {
				return task;
			}
		}
		return null;
	}
}
