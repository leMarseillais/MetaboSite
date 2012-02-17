package TaskManagement;

import org.apache.log4j.Logger;

public class TaskLauncher extends Thread implements Runnable {
	private static Logger LOGGER = Logger.getLogger(TaskLauncher.class);
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
