package TaskManagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class TaskManager {
	private static Logger LOGGER = Logger.getLogger(TaskManager.class);
	private ExecutorService taskExecutor;
	private static TaskManager instance;

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
		this.taskExecutor=Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors());

	}
	
	public void addTask(Task task){
		TaskLauncher launcher = new TaskLauncher(task);
		this.taskExecutor.submit(launcher);
	}


}
