package TaskManagement;

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
			taskManager.addTask(new Test1());
			taskManager.addTask(new Test2());
			taskManager.addTask(new Test3());
			
		}
		
	}

	public static class Test1 implements Task {
		private static Integer cpt1=0;
		@Override
		public void launch() {
			System.out.println("test1 "+cpt1);
			cpt1++;
		}
		

	}
	public static class Test2 implements Task{
		private static Integer cpt2=0;
		@Override
		public void launch() {
			System.out.println("test2 "+cpt2);
			cpt2++;
		}
		
	}
	public static class Test3 implements Task{
		private static Integer cpt3=0;
		@Override
		public void launch() {
			System.out.println("test3 "+cpt3);
			cpt3++;
		}
		
	}
}