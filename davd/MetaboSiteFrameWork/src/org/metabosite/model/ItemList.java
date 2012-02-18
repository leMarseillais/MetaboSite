package org.metabosite.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ItemList {
	
	private Map<String, Item> items = new HashMap<String, Item>();
	
	public Iterator<Item> iterator() {
		return items.values().iterator();
	}
	
	public Iterator<String> keysIterator() {
		return items.keySet().iterator();
	}
	
	public int size() {
		return items.size();
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void addItem(Item i) {
		items.put(i.getName(), i);
	}
	
	public void removeItem(Item i) {
		items.remove(i.getName());
	}
	
	public Item get(String name) {
		return items.get(name);
	}
	
	public Item get(Item i) {
		return items.get(i.getName());
	}


}
