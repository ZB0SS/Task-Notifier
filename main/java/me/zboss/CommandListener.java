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
            System.out.println("ok");
        }
        input = i.nextLine();
        listen(input, i);
    }}
