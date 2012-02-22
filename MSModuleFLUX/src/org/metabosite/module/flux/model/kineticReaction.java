package org.metabosite.module.flux.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.LocalParameter;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class kineticReaction {
	private String reactionName;
	private String formula;
	private ArrayList<String> kinetic = new ArrayList<String>();
	
	public void setName(String name){
		reactionName=name;
	}
	
	public void setFormula(String formula){
		this.formula=formula;
	}
	
	public void setKinetic(String s){
		kinetic.add(s);
	}
	
	public String getName(){
		return reactionName;
	}
	
	public String getFormula(){
		return formula;
	}
	
	public ArrayList<String> getKinetic(){
		return kinetic;
	}
	
	public String replaceFormula(){
		String rep="";
		rep = formula;
		for (int i = 1; i < kinetic.size(); i+=2) {
			rep = rep.replaceAll(kinetic.get(i-1), kinetic.get(i));
		}
		return rep;
	}
}
