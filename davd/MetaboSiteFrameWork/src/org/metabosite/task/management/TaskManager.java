package org.metabosite.task.management;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManager {
    private static Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    private ExecutorService taskExecutor;
    private static TaskManager instance;
    private static HashMap< String, HashMap< Task, TaskLauncher>> taskList;

    public static synchronized TaskManager getInstance() {
        if (instance == null) {
            instance =
                new TaskManager();
        }
        return instance;
    }

    private TaskManager() {
        init();
    }

    private void init() {
        this.taskExecutor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.taskList =
            new HashMap< String, HashMap< Task, TaskLauncher>>();
    }

    public void addTask(Task task, String login) {
        TaskLauncher launcher =
            new TaskLauncher(task);
        this.taskExecutor.submit(launcher);
        launcher.start();
        if (this.taskList.containsKey(login)) {
            this.taskList.get(login).put(task, launcher);
        } else {
            HashMap< Task, TaskLauncher> map =
                new HashMap< Task, TaskLauncher>();
            map.put(task, launcher);

            this.taskList.put(login, map);
        }
    }

    public Set< Task> getTasksByUser(String login) {
        if (this.taskList.containsKey(login)) {
            HashMap< Task, TaskLauncher> tasks =
                this.taskList.get(login);
            return tasks.keySet();
        } else {
            LOGGER.log(Level.INFO, "No task for this user is launch");
            return null;
        }
    }

    public void killTask(String login, Task task) {
        if (this.taskList.containsKey(login)) {
            HashMap< Task, TaskLauncher> tasks =
                this.taskList.get(login);
            if (tasks.containsKey(task)) {
                tasks.get(task).destroy();
            } else {
                LOGGER.log(Level.INFO, "This task is not launch by this user : " + login);
            }

        } else {
            LOGGER.log(Level.INFO, "No task for this user is launch");
        }
    }

    public HashMap< Task, String> getTaskState(String login) {
        HashMap< Task, String> taskState =
            new HashMap< Task, String>();
        if (this.taskList.containsKey(login)) {
            HashMap< Task, TaskLauncher> tasks =
                this.taskList.get(login);
            Set< Task> taskSet =
                tasks.keySet();
            for (Task task : taskSet) {
                TaskLauncher taskLauncher =
                    tasks.get(task);
                taskState.put(task, taskLauncher.getState().toString());
                System.out.println(taskLauncher.getState().toString());
            }

            return taskState;
        } else {
            LOGGER.log(Level.INFO, "No task for this user is launch");
            return null;
        }
    }

}
