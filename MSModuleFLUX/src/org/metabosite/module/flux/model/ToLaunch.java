package org.metabosite.module.flux.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.stream.XMLStreamException;

import org.metabosite.file.management.FilesManage;
import org.metabosite.task.management.Task;
import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.LocalParameter;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;

import src.entities.Siteuser;
import src.services.FileFacadeLocal;

public class ToLaunch extends Task {

	private File fileIn;
	private Siteuser siteuser;
	private FileFacadeLocal fFacade;
	private ArrayList<GrapheFile> filesOut;
	private FilesManage filesManage;

	public ToLaunch(String id, File in, Siteuser siteuser,
			FileFacadeLocal fFacade) {
		super(id);
		this.fileIn = in;
		this.siteuser = siteuser;
		this.fFacade = fFacade;
		this.filesOut = new ArrayList<GrapheFile>();
		this.filesManage = new FilesManage(fFacade);
	}

	@Override
	public void launch() {
		main();

	}

	public void main() {
		try {
			FilesManage filesManage = new FilesManage(fFacade);
			SBMLReader reader = new SBMLReader();
			SBMLDocument sbmlDocument = null;
			try {
				sbmlDocument = reader.readSBML(fileIn.getPath());
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// je déclare le document comme mon model
			Model model = sbmlDocument.getModel();
			// / je récupére toute les listes de réactions

			ListOf<Reaction> ReactionList = model.getListOfReactions();

			for (Reaction reaction : ReactionList) {

				KineticLaw kineticLaw = reaction.getKineticLaw();
				ListOf<LocalParameter> bases = kineticLaw
						.getListOfLocalParameters();

				kineticReaction kire = new kineticReaction();
				kire.setName(reaction.getName());

				for (LocalParameter localParameter : bases) {
					kire.setKinetic(localParameter.getId());
					kire.setKinetic(Double.toString(localParameter.getValue()));
				}
				kire.setFormula(kineticLaw.getFormula());

				ArrayList<Double> listVal = new ArrayList<Double>();
				ArrayList<Integer> listCon = new ArrayList<Integer>();

				for (int i = 0; i < 50; i += 5) {

					ScriptEngineManager mgr = new ScriptEngineManager();
					ScriptEngine engine = mgr.getEngineByName("JavaScript");

					String val = kire.replaceFormula().replaceAll("[A-Z]",
							Integer.toString(i));
					Double valeur = Math.abs((Double) engine.eval(val));

					listVal.add(valeur);
					listCon.add(i);
				}

				String fileout = "";
				try {
					fileout += ("<?xml version='1.0'?>\r\n");
					fileout += ("<JSChart>\r\n");
					fileout += ("<dataset type='line'>\r\n");
					for (int i = 0; i < listVal.size(); i++) {
						fileout += ("<data unit=\"" + listCon.get(i)
								+ "\" value=\"" + listVal.get(i) + "\"/>\r\n");
					}
					fileout += ("</dataset>\r\n");
					fileout += ("<optionset>\r\n");
					fileout += ("<option set='setAxisColor' value=\"'#656565'\"/>\r\n");
					fileout += ("<option set='setAxisNameColor' value=\"'#4A4A4A'\"/>\r\n");
					fileout += ("<option set='setAxisNameFontSize' value='10'/>\r\n");
					fileout += ("<option set='setAxisNameX' value=\"'Concentration'\"/>\r\n");
					fileout += ("<option set='setAxisNameY' value=\"'Vitesse'\"/>\r\n");
					fileout += ("<option set='setAxisPaddingBottom' value='60'/>\r\n");
					fileout += ("<option set='setAxisPaddingLeft' value='180'/>\r\n");
					fileout += ("<option set='setAxisPaddingRight' value='170'/>\r\n");
					fileout += ("<option set='setAxisPaddingTop' value='65'/>\r\n");
					fileout += ("<option set='setAxisValuesColor' value=\"'#656565'\"/>\r\n");
					fileout += ("<option set='setAxisValuesNumberX' value='6'/>\r\n");
					fileout += ("<option set='setGrid' value='true'/>\r\n");
					fileout += ("<option set='setLabelY' value=\"[0, 'Low']\"/>\r\n");
					fileout += ("<option set='setLabelY' value=\"[500000, 'Medium']\"/>\r\n");
					fileout += ("<option set='setLabelY' value=\"[900000, 'High']\"/>\r\n");
					fileout += ("<option set='setLineColor' value=\"'#8638D5'\"/>\r\n");
					fileout += ("<option set='setShowYValues' value='false'/>\r\n");
					fileout += ("<option set='setTitle' value=\"'"
							+ kire.getName() + "'\"/>\r\n");
					fileout += ("<option set='setTitleColor' value=\"'#505050'\"/>\r\n");
					fileout += ("<option set='setSize' value='716, 421'/>\r\n");
					fileout += ("<option set='setTextPaddingBottom' value='20'/>\r\n");
					fileout += ("<option set='setTextPaddingLeft' value='120'/>\r\n");
					fileout += ("<option set='setTextPaddingTop' value='15'/>\r\n");
					fileout += ("<option set='setFlagRadius' value='6'/>\r\n");
					fileout += ("<option set='setTooltip' value=\"[25, 'Tooltip for value 25 on X axis']\"/>\r\n");
					fileout += ("<option set='setTooltip' value=\"[40, 'Tooltip for value 40 on X axis']\"/>\r\n");
					fileout += ("</optionset>\r\n");
					fileout += ("</JSChart>\r\n");

				} catch (Error e1) {

				}
				this.filesOut.add(new GrapheFile(this.filesManage.saveFile(
						"XML code for flux visualisation", "xml", "xml",
						siteuser, "Flux" + kire.getName(), fileout), kire.getName()));
			}
		} catch (ScriptException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<GrapheFile> getFilesOut() {
		return filesOut;
	}
	
	

	
}
