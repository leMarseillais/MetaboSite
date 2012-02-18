package org.metabosite.task.management;

import java.util.logging.Logger;


public class TaskLauncher extends Thread implements Runnable {
	private static Logger LOGGER = Logger.getLogger(TaskLauncher.class.getName());
	private Task task;

	public TaskLauncher(Task task) {
		super();
		this.task = task;
	}

	@Override
	public void run() {
		this.task.launch();
	}

}
