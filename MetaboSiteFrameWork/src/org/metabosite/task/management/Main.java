package org.metabosite.task.management;

import java.util.Set;

public class Main {

	private static TaskLauncher launcher;
	private static TaskLauncher launcher2;
	private static TaskLauncher launcher3;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaskManager taskManager = TaskManager.getInstance();
		for (int i = 0; i < 10; i++) {
			System.out.println("nlsjnc");
			taskManager.addTask(new Test1(),"ll");
			taskManager.addTask(new Test2(),"oo");
			taskManager.addTask(new Test3(),"pp");
			Set<Task> tasks=taskManager.getTasksByUser("ll");
			for (Task task : tasks) {
				System.out.println(task.getClass().getName());
			}
			taskManager.getTaskState("ll");
		}
		
	}

	public static class Test1 extends Task {
		public String name ="jjj";
		private static Integer cpt1=0;
		@Override
		public void launch() {
			System.out.println("test1 "+cpt1);
			cpt1++;
		}
		

	}
	public static class Test2 extends Task{
		private static Integer cpt2=0;
		@Override
		public void launch() {
			System.out.println("test2 "+cpt2);
			cpt2++;
		}
		
	}
	public static class Test3 extends Task{
		private static Integer cpt3=0;
		@Override
		public void launch() {
			System.out.println("test3 "+cpt3);
			cpt3++;
		}
		
	}
}