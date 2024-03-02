import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UI {
    protected static final String NAME = "Krot"; // Static variable for the bot's name
    protected boolean hasEnded; // Static variable to end the chatbot

    public UI () {
        this.hasEnded = false;
    }

    public void printUserGuide() {
        System.out.println("Here's a list of commands that I take:");
        System.out.println("\tOpen the commands list: commands");
        System.out.println("\tSee your list of tasks: list");
        System.out.println("\tAdd a task to the list: todo <task>");
        System.out.println("\tAdd a task with deadline to the list: deadline <task> /by <date>");
        System.out.println("\tAdd an event to the list: event <event name> /from <date> /to <date>");
        System.out.println("\tMark the tasks as done: mark <taskNumber>");
        System.out.println("\tUnmark the tasks as done: unmark <taskNumber>");
        System.out.println("\tDelete the task: delete <taskNumber>");
        System.out.println("\tFind all the tasks due from now to desired date: find /date <YYYY-MM-DD>");
        System.out.println("\tEnd the chat session: bye");
    }

    public void printUser() {
        System.out.println("You:");
    }

    public void printName() {
        // Prints the name of bot when replying
        System.out.println(NAME + ":");
    }

    public void greeting() {
        // Prints out the standard greeting
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
    }

    public void printSeparator() {
        // Prints line separators
        System.out.println("-");
    }

    public void printError(String message) {
        // Prints error message
        System.out.println(message);
    }

    public void endChat() {
        // Prints closing message
        System.out.println("Bye. Hope to see you again soon!");
        hasEnded = true;
    }

    public void listTasks(ArrayList<Task> tasks) {
        // Prints the list of tasks
        int i = 1;
        if (tasks.isEmpty()) {
            System.out.println("There's no tasks to do! :)");
        }
        for (Task task : tasks) {
            System.out.print(i + ".");
            printTask(task);
            i += 1;
        }
    }

    public void printTask(Task task) {
        // Prints specified task
        System.out.println("["
                + task.getTaskType() + "]"
                + "["
                + (task.isDone ? "X" : " ")
                + "] "
                + task.task
                + (task.getTaskType().equalsIgnoreCase("E") ? " (from: " + reformatDate(task.getStart()) + " " : "")
                + (task.getTaskType().equalsIgnoreCase("E") ? "to: " + reformatDate(task.getEnd()) + ")" :
                task.getTaskType().equalsIgnoreCase("D") ? " (by: " + reformatDate(task.getEnd()) + ")" : ""));
    }

    public String reformatDate(LocalDateTime date) {
        String dateTime;
        dateTime = date.format(DateTimeFormatter.ofPattern("dd MMM yy hh:mma"));
        return dateTime;
    }

    public void printMarkMessage(Task task) {
        // Prints response message after marking
        if (task.isDone) {
            System.out.println("Wow good job at clearing a task! I,ve marked this task as done:");
        } else {
            System.out.println("More to do? I've marked this task as not done yet:");
        }
        printTask(task);
    }

    public void printCreateTaskMessage(Task task, int size) {
        // Prints response message after creating a task
        System.out.println("More tasks to do! I've added:");
        printTask(task);
        System.out.println("Get to working, you now have " + size + " tasks in the list!");
    }

    public void printDeleteMessage(Task task, int index, int size) {
        // Prints response message after deleting a task
        System.out.println("Deleted task " + index + " from the list.");
        printTask(task);
        System.out.println("You now have " + size + " tasks to complete.");
    }
}
