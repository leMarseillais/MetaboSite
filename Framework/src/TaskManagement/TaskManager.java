package TaskManagement;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class TaskManager {
	private static Logger LOGGER = Logger.getLogger(TaskManager.class);
	private ExecutorService taskExecutor;
	private static TaskManager instance;
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
		this.taskExecutor=Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors());
		this.taskList=new HashMap<String, HashMap<Task,TaskLauncher>>();
	}
	
	public void addTask(Task task, String login){
		TaskLauncher launcher = new TaskLauncher(task);
		this.taskExecutor.submit(launcher);
		if (this.taskList.containsKey(login)){
			this.taskList.get(login).put(task, launcher);
		}else {
			HashMap<Task, TaskLauncher> map = new HashMap<Task, TaskLauncher>();
			map.put(task, launcher);
			
			this.taskList.put(login, map);
		}
	}
	
	public Set<Task> getTasksByUser(String login){
		if (this.taskList.containsKey(login)){
			HashMap<Task, TaskLauncher> tasks= this.taskList.get(login);
			return tasks.keySet();
		}else{
			LOGGER.log(Priority.INFO, "No task for this user is launch");
			return null;
		}
	}
	
	public void killTask(String login,Task task){
		if (this.taskList.containsKey(login)){
			HashMap<Task, TaskLauncher> tasks=this.taskList.get(login);
			if (tasks.containsKey(task)){
				tasks.get(task).destroy();
			}else{
				LOGGER.log(Priority.INFO, "This task is not launch by this user : "+login);
			}
			
		}else{
			LOGGER.log(Priority.INFO, "No task for this user is launch");
		}
	}

}
