package org.metabosite.module.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTree;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ModifierSpeciesReference;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SpeciesReference;


/** Displays the content of an SBML file in a {@link JTree} */
public class JSBMLvisualiser extends Reaction {

	private HashMap reactions;
	
	public JSBMLvisualiser(SBMLDocument document) throws IOException, XMLStreamException {
		//je déclare le document comme mon model 
		Model model = document.getModel();
		// / je récupére toute les listes de réactions
		
		ListOf<Reaction> ReactionList = model.getListOfReactions();
		
		ArrayList reactantList=new ArrayList();
		ArrayList productList=new ArrayList();
		ArrayList enzymeList=new ArrayList();

		this.reactions= new HashMap();
		
		for (int i = 0; i < ReactionList.size(); i++) {
			//récupération des listes Reactants, produits, modifiers presentent dans le fichier sbml.
			ListOf<SpeciesReference> listReactant= ReactionList.get(i).getListOfReactants();
			ListOf<SpeciesReference> listProduct=ReactionList.get(i).getListOfProducts();				
			ListOf<ModifierSpeciesReference> listModifier=ReactionList.get(i). getListOfModifiers();
			//
			ArrayList rpeList = new ArrayList();
			//traitement de ces listes
			//modification de la liste des enzymes
			
			ArrayList enzymesL=new ArrayList();
			for (int a=0; a<listModifier.size(); a++){
					ModifierSpeciesReference modifier=listModifier.get(a);
						//ModifierSpeciesReference MM=listModifier.get(jjj);
					String enzyme=modifier.toString();
					Pattern p0 = Pattern.compile("beta");
					Matcher m0=p0.matcher(enzyme);
					String s0=m0.replaceAll("ß");
					Pattern p1 = Pattern.compile("space");
					Matcher m1=p1.matcher(s0);
					String s1=m1.replaceAll(" ");
					Pattern p2= Pattern.compile("_");
					Matcher m2=p2.matcher(s1);
					String s2=m2.replaceAll("");
					Pattern p3= Pattern.compile("alpha");
					Matcher m3=p3.matcher(s2);
					String s3=m3.replaceAll("α");
					Pattern p4= Pattern.compile("minus");
					Matcher m4=p4.matcher(s3);
					String s4=m4.replaceAll("-");
					//RPMliste.add(s4);
					enzymesL.add(s4);
				}
				rpeList.add(0,enzymesL);
			
			
			//liste des reactants
			ArrayList reactantL=new ArrayList();
			for (int b=0; b< listReactant.size();b++){
				//System.out.println(listReactant.get(j));
				SpeciesReference reactants=listReactant.get(b);
				//on les transforme en string
				String reactant=reactants.toString();
				Pattern p0 = Pattern.compile("beta");
				Matcher m0=p0.matcher(reactant);
				String s0=m0.replaceAll("ß");
				Pattern p1 = Pattern.compile("space");
				Matcher m1=p1.matcher(s0);
				String s1=m1.replaceAll(" ");
				Pattern p2= Pattern.compile("_");
				Matcher m2=p2.matcher(s1);
				String s2=m2.replaceAll("");
				Pattern p3= Pattern.compile("alpha");
				Matcher m3=p3.matcher(s2);
				String s3=m3.replaceAll("α");
				Pattern p4= Pattern.compile("minus");
				Matcher m4=p4.matcher(s3);
				String s4=m4.replaceAll("-");
				//RPMliste.add(s4);
				reactantL.add(s4);
			}
			rpeList.add(1,reactantL);	//reactants
			

			
			//modification de la liste des Produits
			ArrayList productL=new ArrayList();
			for (int c=0; c<listProduct.size(); c++){
					SpeciesReference products=listProduct.get(c);
					String pp=products.toString();
					Pattern p0 = Pattern.compile("beta");
					Matcher m0=p0.matcher(pp);
					String s0=m0.replaceAll("ß");
					Pattern p1 = Pattern.compile("space");
					Matcher m1=p1.matcher(s0);
					String s1=m1.replaceAll(" ");
					Pattern p2= Pattern.compile("_");
					Matcher m2=p2.matcher(s1);
					String s2=m2.replaceAll("");
					Pattern p3= Pattern.compile("alpha");
					Matcher m3=p3.matcher(s2);
					String s3=m3.replaceAll("α");
					Pattern p4= Pattern.compile("minus");
					Matcher m4=p4.matcher(s3);
					String s4=m4.replaceAll("-");
					//RPMliste.add(s4);
					productL.add(s4);
						
				}
			rpeList.add(2,productL);
		
			
			reactions.put(ReactionList.get(i),rpeList);
		}

		
	}

	public void write(File file) throws IOException, XMLStreamException{
		FileWriter fw= new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
			
			pw.write("var labelType, useGradients, nativeTextSupport, animate;\r\n");
			pw.write("(function() { \r\n");
			pw.write("var ua = navigator.userAgent, \r\n");
			pw.write("iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),\r\n");
			pw.write("typeOfCanvas = typeof HTMLCanvasElement,\r\n");
			pw.write("nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),\r\n");
			pw.write("textSupport = nativeCanvasSupport \r\n ");
			pw.write(" && (typeof document.createElement('canvas').getContext('2d').fillText == 'function'); \r\n");
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
			pw.write("labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';\r\n");
			pw.write("nativeTextSupport = labelType == 'Native'; \r\n");
			pw.write("useGradients = nativeCanvasSupport;");
			pw.write("animate = !(iStuff || !nativeCanvasSupport); \r\n");
			pw.write("})();\r\n");
			pw.write("var Log = {\r\n");
			pw.write("elem: false, \r\n");
		    pw.write("write: function(text){ \r\n");
		    pw.write("if (!this.elem)  \r\n");
		    pw.write("this.elem = document.getElementById('log'); \r\n");
		    pw.write("this.elem.innerHTML = text; \r\n");
		    pw.write("this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px'; \r\n");
		    pw.write("} \r\n");
		    pw.write("}; \r\n");
		    pw.write("function init(){ \r\n");
		    pw.write(" var json = [ \r\n");
		    
		    for (Iterator iter = this.reactions.keySet().iterator(); iter.hasNext();) {
				Reaction key = (Reaction) iter.next();
				//System.out.println("key = " + key + " value = "+ MapReaction.get(key));
				ArrayList RPE=(ArrayList) this.reactions.get(key);
				ArrayList enzy = (ArrayList) RPE.get(0);
				ArrayList reac = (ArrayList)RPE.get(1);
				ArrayList prod = (ArrayList)RPE.get(2);
				
				String enz=(String) enzy.get(0);
				for (int i=1;i<enzy.size();i++){
					enz +=" & "+(String) enzy.get(i);
									
				}
				pw.write("{\r\n ");
				pw.write("'adjacencies': [ \r\n ");
				for (int i=0; i<reac.size();i++){
					pw.write("{ \r\n");
					pw.write("'nodeTo':"+"'"+reac.get(i)+ "', \r\n");
					pw.write("'nodeFrom':"+"'" + enz +"', \r\n");
					pw.write("'data':{} \r\n");
					pw.write("}, \r\n");

				}
				
				pw.write("], \r\n");
				pw.write("'data': { \r\n");
				pw.write("'$color': '#C74243', ");
				pw.write("'$type': 'triangle', \r\n");
				pw.write( " '$dim': 15 \r\n");
				pw.write("}, \r\n");
				System.out.println(enz);
				System.out.println("+++++++++");
				pw.write("'id': "+"'"+enz+"',\r\n");
				pw.write( "'name': "+"'"+enz+"'\r\n");
				pw.write("},\r\n"); 
				
				/************************************/
				for (int i=0;i<reac.size();i++){
					pw.write("{\r\n ");
					pw.write("'adjacencies': [ ], \r\n ");
					//pw.write(  "'"+enz+"'\r\n");
					//LISTE VIDE 				
					//pw.write("], ");
					pw.write("'data': { \r\n");
					pw.write("'$color': '#416D9C', ");
					pw.write("'$type': 'square', \r\n");
					pw.write( " '$dim': 15 \r\n");
					pw.write("}, \r\n");
					pw.write("'id': "+"'"+reac.get(i)+"',\r\n");
					pw.write( "'name': "+"'"+reac.get(i)+"'\r\n");
					pw.write("},\r\n");
									
				}
				
				
				
				/////////////////////////////////////////
				pw.write("{\r\n ");
				pw.write("'adjacencies': [ \r\n ");
				for (int i=0; i<prod.size();i++){
					pw.write("{ \r\n");
					pw.write("'nodeTo':"+"'"+ prod.get(i)+ "', \r\n");
					pw.write("'nodeFrom' :"+"'" + enz +"', \r\n");
					pw.write("'data':{} \r\n");
					pw.write("},\r\n");
				}
				pw.write("], \r\n");
				pw.write("'data': { \r\n");
				pw.write("'$color': '#C74243', ");
				pw.write("'$type': 'triangle', \r\n");
				pw.write( " '$dim': 15 \r\n");
				pw.write("}, \r\n");
				
				pw.write("'id': "+"'"+enz+"',\r\n");
				pw.write( "'name': "+"'"+enz+"'\r\n");
				pw.write("},\r\n"); 
				
				
				/************************************/
				for (int i=0;i<prod.size();i++){
					pw.write("{\r\n ");
					pw.write("'adjacencies': [ ], \r\n ");
					//pw.write(  "'"+enz+"'\r\n");				
					//pw.write("], \r\n");
					pw.write("'data': { \r\n");
					pw.write("'$color': '#416D9C', ");
					pw.write("'$type': 'square', \r\n");
					pw.write( " '$dim': 15 \r\n");
					pw.write("}, \r\n");
					pw.write("'id': "+"'"+prod.get(i)+"',\r\n");
					pw.write( "'name': "+"'"+prod.get(i)+"'\r\n");
					pw.write("},\r\n");
									
				}
				
				
			}

		    
		    
		    
		    
		    pw.write("  ];\r\n");
		    pw.write("var fd = new $jit.ForceDirected({\r\n");
		    pw.write("injectInto: 'infovis',\r\n");
		    pw.write("Navigation: {\r\n");
		    pw.write("enable: true,\r\n");
		    pw.write("panning: 'avoid nodes',\r\n");
		    pw.write("zooming: 10 \r\n");//zoom speed. higher is more sensible
		    pw.write("  },\r\n");
		    pw.write("Node: {\r\n");
		    pw.write("overridable: true\r\n");
		    pw.write("},\r\n");
		    pw.write("Edge: {\r\n");
		    pw.write("overridable: true,\r\n");
		    pw.write("color: '#23A4FF',\r\n");
		    pw.write("lineWidth: 0.4\r\n");
		    pw.write("},\r\n");
		    pw.write("Label: {\r\n");
		    pw.write("type: labelType,\r\n"); //Native or HTML
		    pw.write(" size: 10,\r\n");
	   	  	pw.write("style: 'bold'\r\n");
	   	  	pw.write("},\r\n");
	   	  	//Add Tips
	   	  	pw.write(" Tips: {\r\n");
	   	  	pw.write(" enable: true,\r\n");
	   	  	pw.write(" onShow: function(tip, node) {\r\n");
			    //count connections
	   	  	pw.write("  var count = 0;\r\n");
	   	  	pw.write("node.eachAdjacency(function() { count++; });\r\n");
			    //display node info in tooltip
		  /*pw.write("tip.innerHTML = '<div class=\'tip-title\'>' + node.name + '</div>'\r\n");
		  pw.write("+ '<div class=\'tip-text\'><b>connections:</b> ' + count + '</div>';\r\n");*/
		  
		  
	   	  	pw.write("tip.innerHTML = '<div class= \\'tip-title\\'>' + node.name + '</div>'+'<div class=\\'tip-text\\'><b>connections:</b> ' + count + '</div>';\r\n");
		
	   	  	pw.write("}\r\n");
	   	  	pw.write("},\r\n");
			  // Add node events
	   	  	pw.write("Events: {\r\n");
		    pw.write("enable: true,\r\n");
	        pw.write("type: 'Native',\r\n");
				  //Change cursor style when hovering a node
	        pw.write("onMouseEnter: function() {\r\n");
	        pw.write("fd.canvas.getElement().style.cursor = 'move';\r\n");
	        pw.write("},\r\n");
	        pw.write("onMouseLeave: function() {\r\n");
	        pw.write("fd.canvas.getElement().style.cursor = '';\r\n");
	        pw.write("},\r\n");
				  //Update node positions when dragged
	        		
	        pw.write("onDragMove: function(node, eventInfo, e) {\r\n");
			pw.write("var pos = eventInfo.getPos();\r\n");
			pw.write("node.pos.setc(pos.x, pos.y);\r\n");
		    pw.write("fd.plot();\r\n");
			pw.write(" },\r\n");
				  //Implement the same handler for touchscreens
			pw.write("onTouchMove: function(node, eventInfo, e) {\r\n");
			pw.write("$jit.util.event.stop(e); \r\n");//stop default touchmove event
			pw.write(" this.onDragMove(node, eventInfo, e);\r\n");
			pw.write("},\r\n");
				  //Add also a click handler to nodes
			pw.write("onClick: function(node) {\r\n");
			pw.write("if(!node) return;\r\n");
				    // Build the right column relations list.
				    // This is done by traversing the clicked node connections.
			pw.write(" var html = '<h4>' + node.name + '</h4><b> connections:</b><ul><li>',\r\n");
			pw.write(" list = [];\r\n");
			pw.write("node.eachAdjacency(function(adj){\r\n");
			pw.write(" list.push(adj.nodeTo.name);\r\n");
			pw.write(" });\r\n");
				    //append connections information
			pw.write("$jit.id('inner-details').innerHTML = html + list.join('</li><li>') + '</li></ul>';\r\n");
			pw.write("}\r\n");
			pw.write("},\r\n");
				  //Number of iterations for the FD algorithm
			pw.write("iterations: 200,\r\n");
				  //Edge length
			pw.write("levelDistance: 130,\r\n");
				  // Add text to the labels. This method is only triggered
				  // on label creation and only for DOM labels (not native canvas ones).
			pw.write("onCreateLabel: function(domElement, node){\r\n");
			pw.write("domElement.innerHTML = node.name;\r\n");
			pw.write("var style = domElement.style;\r\n");
			pw.write("style.fontSize = '0.8em';\r\n");
		    pw.write("style.color = '#ddd';\r\n");
			pw.write("},\r\n");
				  // Change node styles when DOM labels are placed
				  // or moved.
		    pw.write("onPlaceLabel: function(domElement, node){\r\n");
			pw.write("var style = domElement.style;\r\n");
			pw.write("var left = parseInt(style.left);\r\n");
			pw.write("var top = parseInt(style.top);\r\n");
			pw.write("var w = domElement.offsetWidth;\r\n");
			pw.write("style.left = (left - w / 2) + 'px';\r\n");
			pw.write("style.top = (top + 10) + 'px';\r\n");
			pw.write("style.display = '';\r\n");
			pw.write("}\r\n");
			pw.write("});\r\n");
				  // load JSON data.
			pw.write("fd.loadJSON(json);\r\n");
				  // compute positions incrementally and animate.
			pw.write("fd.computeIncremental({\r\n");
			pw.write("iter: 40,\r\n");
			pw.write("property: 'end',\r\n");
			pw.write("onStep: function(perc){\r\n");
			pw.write("Log.write(perc + '% loaded...');\r\n");
			pw.write("},\r\n");
			pw.write("onComplete: function(){\r\n");
			pw.write("Log.write('done');\r\n");
			pw.write("fd.animate({\r\n");
		    pw.write("modes: ['linear'],\r\n");
			pw.write("transition: $jit.Trans.Elastic.easeOut,\r\n");
			pw.write("duration: 2500\r\n");
			pw.write("});\r\n");
			pw.write("}\r\n");
			pw.write("});\r\n");
				  // end
			pw.write("}\r\n");
		  
			pw.close();  
		}
		
	

}
