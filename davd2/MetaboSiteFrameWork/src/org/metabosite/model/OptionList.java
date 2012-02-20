package org.metabosite.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OptionList {
	
	private Map<String, Option> options = new HashMap<String, Option>();
	
	public Iterator<Option> iterator() {
		return options.values().iterator();
	}
	
	public Iterator<String> keysIterator() {
		return options.keySet().iterator();
	}
	
	public int size() {
		return options.size();
	}
	
	public boolean isEmpty() {
		return options.isEmpty();
	}

	public void addOption(Option o) {
		options.put(o.getName(), o);
	}
	
	public void removeOption(Option o) {
		options.remove(o.getName());
	}
	
	public Option get(String name) {
		return options.get(name);
	}
	
	public Option get(Option o) {
		return options.get(o.getName());
	}

}
