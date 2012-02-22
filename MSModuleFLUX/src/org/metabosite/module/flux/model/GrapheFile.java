package org.metabosite.module.flux.model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

public class GrapheFile {

	String path;
	String name;
	
	public GrapheFile(String path, String name) {
		this.path = path;
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
