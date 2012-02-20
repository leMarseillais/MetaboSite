package org.metabosite.task.management;

public class TaskLauncher extends Thread implements Runnable {
	private Task task;

	public TaskLauncher(Task task) {
		super();
		this.task = task;
	}

	@Override
	public void run() {
		this.task.launch();
		this.task.setTerminated(true);
	}

}
