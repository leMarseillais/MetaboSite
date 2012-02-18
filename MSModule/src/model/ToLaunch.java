package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.metabosite.task.management.Task;
import org.sbml.jsbml.SBMLReader;

public class ToLaunch implements Task {

	File file;
	
	public ToLaunch(File file) {
		this.file=file;
	}
	
	@Override
	public void launch() {
		try {
			JSBMLvisualiser jvi= new JSBMLvisualiser((new SBMLReader()).readSBML(file.getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
