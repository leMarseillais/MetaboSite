package org.metabosite.model;

public abstract class Option {
	
	private String name;
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Option(String name) {
		this.name = name;
	}
}
