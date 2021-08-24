package me.zboss;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

public class Task {
    private String taskName;
    private String time;
    private boolean isAM;
    public static ArrayList<Integer> times = new ArrayList<Integer>();

    public Task(String taskName, boolean isAM, String time) {
        this.taskName = taskName;
        this.isAM = isAM;
        this.time = time;

        HashMap<String, String> tasks = new HashMap<String, String>();

        if (isAM) {
            tasks.put(taskName, time + " (AM)");
        } else {
            tasks.put(taskName, time + " (PM)");
        }
    }

    public void setTask() {
        String[] timesString = this.time.split(":");
        for (String time: timesString) {
            times.add(Integer.parseInt(time));
        }

        // The time now
        LocalDateTime localDateTime = LocalDateTime.now();
        // If its pm then add 12 eg(1 + 12 = 13)
        if (!isAM) {
            times.set(0, times.get(0) + 12);
        }

        int hoursAway = times.get(0) - localDateTime.getHour();
        int minutesAway = times.get(1) - localDateTime.getMinute();

        long millisecondsAway = ((minutesAway * 60L + (hoursAway * 60L) * 60) * 1000) - localDateTime.getSecond() * 1000;

        String taskName = this.getTaskName();
        Timer myTimer = new Timer();

        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Its time for your task " + taskName);
            }
        };

        myTimer.schedule(myTask, millisecondsAway);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isAM() {
        return isAM;
    }

    public void setAM(boolean AM) {
        isAM = AM;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
