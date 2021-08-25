package me.zboss;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

public class Task {
    final private String taskName;
    final private String time;
    final private boolean isAM;
    public static HashMap<String, String> tasks = new HashMap<String, String>();
    public static HashMap<String, Task> getTask = new HashMap<String, Task>();

    private boolean remove = false;

    public Task(String taskName, boolean isAM, String time) {
        this.taskName = taskName;
        this.isAM = isAM;
        this.time = time;

        if (isAM) {
            tasks.put(taskName, time + " (AM)");
        } else {
            tasks.put(taskName, time + " (PM)");
        }
        getTask.put(this.taskName, this);
    }

    public void setTask() {
        ArrayList<Integer> times = new ArrayList<Integer>();
        boolean goOn = false;
        try {
            String[] timesString = this.time.split(":");
            goOn = true;
        } catch (Exception e) {
            System.out.println("You didn't enter a valid time");

        }
        if  (goOn) {
            String[] timesString = this.time.split(":");
            for (String time : timesString) {
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
                    if (!remove) {
                        System.out.println("Its time for your task " + taskName);
                    }
                }
            };

            myTimer.schedule(myTask, millisecondsAway);
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isAM() {
        return isAM;
    }

    public String getTime() {
        return time;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
        if (remove) {
            tasks.remove(this.taskName);
        }

    }

    public static Task getTaskByTaskName(String name) {
        if (getTask.containsKey(name)) {
            return getTask.get(name);
        } else {
            return null;
        }
    }

}
