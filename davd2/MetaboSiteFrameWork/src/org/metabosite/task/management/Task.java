package org.metabosite.task.management;

public abstract class Task {

	private String id;
	private boolean terminated = false;
	
	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Task(String id) {
		this.id = id;
	}
	
	public abstract void launch();
}
