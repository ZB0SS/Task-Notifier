package me.zboss;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Task myTask = new Task("i", false, "8:31");
        myTask.setTask();
        CommandListener myListener = new CommandListener();
        myListener.start();
    }
}
