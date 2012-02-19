package org.metabosite.module.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;

import org.metabosite.file.mangement.FilesManage;
import org.metabosite.task.management.Task;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;

import src.entities.Siteuser;




public class ToLaunch implements Task {

	private File fileIn;
	private Siteuser siteuser;
	private String fileout;
	
	public ToLaunch(File in,Siteuser siteuser) {
		this.fileIn=in;
		this.siteuser=siteuser;
	}
	
	@Override
	public void launch() {
		try {
			SBMLReader reader =new SBMLReader();
			SBMLDocument sbmlDocument=reader.readSBML(fileIn.getPath());
			JSBMLvisualiser jvi= new JSBMLvisualiser(sbmlDocument);
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Parssage du sbml");
			FilesManage filesManage = new FilesManage();
			this.fileout=jvi.write();
			filesManage.saveFile("Javascript code for pathway visualisation", "js", ".js", siteuser, "Visu", this.fileout);
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Žcriture du JS");
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

	public String getFileout() {
		return fileout;
	}
	
	

}
