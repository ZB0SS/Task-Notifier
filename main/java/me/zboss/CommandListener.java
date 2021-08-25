package me.zboss;
import java.util.Objects;
import java.util.Scanner;

public class CommandListener extends Thread{
    public void run() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Welecome to Task Scheduler type help for commands");
        listen(myScanner.nextLine(), myScanner);

    }

    private void listen(String input, Scanner i) {
        if (Objects.equals(input, "help")) {
            System.out.println("list - Lists all your tasks \n" +
                                "create - creates a new task \n" +
                                "remove - removes a task\n" +
                                "find - gets you the information of a task\n");
        }
        else if (Objects.equals(input, "list")) {
            if (Task.tasks.size() == 0) {
                System.out.println("You have no tasks, use the use the create command to createe a command");
            }
            for (String task : Task.tasks.keySet()) {
                System.out.println(task + " " + Task.tasks.get(task));
            }
        }
        else if (Objects.equals(input, "create")) {
            System.out.println("What is the name of the task?");
            String taskName = i.nextLine();
            System.out.println("What time should I notify you to do this task? (xx:yy)");
            String time = i.nextLine();
            System.out.println("Is it AM (true/false)");
            boolean isAM = false;
            boolean goOn = false;
            try {
                isAM = i.nextBoolean();
                goOn = true;
            } catch (Exception e) {
                System.out.println("Next time type true or false\n");
            }
            if (goOn) {
                Task newTask = new Task(taskName, isAM, time);
                newTask.setTask();
                System.out.println("Succesfully created task " + taskName);
            }
        }
        else if (Objects.equals(input, "remove")) {
            System.out.println("What task would you like to remove (specify the task name)");
            String taskName = i.nextLine();
            if (Task.getTaskByTaskName(taskName) != null) {
                Task taskToRemove = Task.getTaskByTaskName(taskName);
                taskToRemove.setRemove(true);
                System.out.println("Succesfully removed " + taskName);
            } else {
                System.out.println("The task " + taskName + " does not exist. (case matters!)");
            }
        } else if (Objects.equals(input, "find")) {
            System.out.println("Specify the name of the task you want to find");
            String taskName = i.nextLine();
            if (Task.getTaskByTaskName(taskName) != null) {
                System.out.println(taskName + " " + Task.tasks.get(taskName));
            } else {
                System.out.println("The task " + taskName + " does not exist. (case matters!)");
            }
        }
        input = i.nextLine();
        listen(input, i);
    }


}

