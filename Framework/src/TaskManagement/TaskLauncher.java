package TaskManagement;

import org.apache.log4j.Logger;

public class TaskLauncher implements Runnable {
	private static Logger LOGGER = Logger.getLogger(TaskLauncher.class);
	private Task task;

	public TaskLauncher(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		this.task.launch();
	}

}
