package org.metabosite.task.management;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class TaskManager {
	@SuppressWarnings("unused")
	private static Logger LOGGER = Logger
			.getLogger(TaskManager.class.getName());
	private ExecutorService taskExecutor;
	private static TaskManager instance = null;
	private static HashMap<String, HashMap<Task, TaskLauncher>> taskList;

	public static synchronized TaskManager getInstance() {
		if (instance == null) {
			instance = new TaskManager();
		}
		return instance;
	}

	private TaskManager() {
		init();
	}

	private void init() {
		this.taskExecutor = Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors());
		TaskManager.taskList = new HashMap<String, HashMap<Task, TaskLauncher>>();
	}

	public void addTask(Task task, String login) {
		TaskLauncher launcher = new TaskLauncher(task);
		this.taskExecutor.submit(launcher);
		
		if (TaskManager.taskList.containsKey(login)) {
			TaskManager.taskList.get(login).put(task, launcher);
		} else {
			HashMap<Task, TaskLauncher> map = new HashMap<Task, TaskLauncher>();
			map.put(task, launcher);

			TaskManager.taskList.put(login, map);
		}
	}

	public Set<Task> getTasksByUser(String login) {
		if (TaskManager.taskList.containsKey(login)) {
			HashMap<Task, TaskLauncher> tasks = TaskManager.taskList.get(login);
			return tasks.keySet();
		} else {
			return null;
		}
	}

	public Task getTask(String login, String id) {
		if (TaskManager.taskList.containsKey(login)) {
			HashMap<Task, TaskLauncher> tasks = TaskManager.taskList.get(login);
			for (Iterator<Task> it = tasks.keySet().iterator(); it.hasNext();) {
				Task t = it.next();
				if (t.getId().equals(id)) {
					return t;
				}
			}
		} else {
			return null;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public void killTask(String login, Task task) {
		if (TaskManager.taskList.containsKey(login)) {
			HashMap<Task, TaskLauncher> tasks = TaskManager.taskList.get(login);
			if (tasks.containsKey(task)) {
				tasks.get(task).destroy();
			} else {
			}
		}
	}

	public HashMap<Task, String> getTaskState(String login) {
		HashMap<Task, String> taskState = new HashMap<Task, String>();
		if (TaskManager.taskList.containsKey(login)) {
			HashMap<Task, TaskLauncher> tasks = TaskManager.taskList.get(login);
			Set<Task> taskSet = tasks.keySet();
			for (Task task : taskSet) {
				TaskLauncher taskLauncher = tasks.get(task);
				taskState.put(task, taskLauncher.getState().toString());
			}

			return taskState;
		} else {
			return null;
		}
	}

}
