package seedu.duke;

import tasks.Task;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Default constructor for TaskList class.
     * Instantiate a new ArrayList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter for size of ArrayList.
     * @return ArrayList size
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Getter for ArrayList of tasks.
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }


    /**
     * Lists today tasks in ArrayList.
     */
    public void listTodayTasks() {
        int sizeOfArray = tasks.size();
        LocalDate currentDateObj = LocalDate.now();
        DateTimeFormatter formattedDateObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currentDate = currentDateObj.format(formattedDateObj);
        LocalDate formattedCurrDate = LocalDate.parse(currentDate, formattedDateObj);
        System.out.println("Here are the tasks you have for today!");
        for (int i = 1; i < sizeOfArray + 1; i++) {
            LocalDate taskDate = tasks.get(i - 1).getDate();
            int testEquals = formattedCurrDate.compareTo(taskDate);
            if (testEquals == 0) {
                String taskNum = Integer.toString(i);
                System.out.println(taskNum + "." + tasks.get(i - 1));
            }
        }
    }

    /**
     * Getter for Task with the provided index in TaskList.
     * @param index index of Task to return
     * @return Task object with corresponding index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to TaskList.
     * @param task task object to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Set the Task corresponding to index specified as done.
     * @param doneIndex index of Task to be marked done
     * @throws IndexOutOfBoundsException throws when index is out of range of size of current TaskList
     */
    public void markTaskAsDone(int doneIndex) throws IndexOutOfBoundsException {
        tasks.get(doneIndex).setDone();
    }

    public void deleteTask(int deleteIndex) throws IndexOutOfBoundsException {
        tasks.remove(deleteIndex);
    }
}