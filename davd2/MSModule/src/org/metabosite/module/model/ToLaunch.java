package org.metabosite.module.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.metabosite.file.management.FilesManage;
import org.metabosite.task.management.Task;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;

import src.entities.Siteuser;
import src.services.FileFacadeLocal;

public class ToLaunch extends Task {

	private File fileIn;
	private Siteuser siteuser;
	private String fileout;
	private FileFacadeLocal fFacade;

	public ToLaunch(String id, File in, Siteuser siteuser, FileFacadeLocal fFacade) {
		super(id);
		this.fileIn = in;
		this.siteuser = siteuser;
		this.fFacade = fFacade;
	}

	@Override
	public void launch() {
		try {
			SBMLReader reader = new SBMLReader();
			SBMLDocument sbmlDocument = reader.readSBML(fileIn.getPath());
			JSBMLvisualiser jvi = new JSBMLvisualiser(sbmlDocument);

			FilesManage filesManage = new FilesManage(fFacade);
			this.fileout = jvi.write();
			filesManage.saveFile("Javascript code for pathway visualisation",
					"js", "js", siteuser, "Visu", this.fileout);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}

	public String getFileout() {
		return fileout;
	}

}
