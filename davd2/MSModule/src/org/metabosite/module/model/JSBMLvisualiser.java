package org.metabosite.module.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	private static final long serialVersionUID = 1L;
	
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

	public String write() throws IOException, XMLStreamException{
		String file="";
		
			
			file+=("var labelType, useGradients, nativeTextSupport, animate;\r\n");
			file+=("(function() { \r\n");
			file+=("var ua = navigator.userAgent, \r\n");
			file+=("iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),\r\n");
			file+=("typeOfCanvas = typeof HTMLCanvasElement,\r\n");
			file+=("nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),\r\n");
			file+=("textSupport = nativeCanvasSupport \r\n ");
			file+=(" && (typeof document.createElement('canvas').getContext('2d').fillText == 'function'); \r\n");
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
			file+=("labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';\r\n");
			file+=("nativeTextSupport = labelType == 'Native'; \r\n");
			file+=("useGradients = nativeCanvasSupport;");
			file+=("animate = !(iStuff || !nativeCanvasSupport); \r\n");
			file+=("})();\r\n");
			file+=("var Log = {\r\n");
			file+=("elem: false, \r\n");
		    file+=("write: function(text){ \r\n");
		    file+=("if (!this.elem)  \r\n");
		    file+=("this.elem = document.getElementById('log'); \r\n");
		    file+=("this.elem.innerHTML = text; \r\n");
		    file+=("this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px'; \r\n");
		    file+=("} \r\n");
		    file+=("}; \r\n");
		    file+=("function init(){ \r\n");
		    file+=(" var json = [ \r\n");
		    
		    for (Iterator iter = this.reactions.keySet().iterator(); iter.hasNext();) {
				Reaction key = (Reaction) iter.next();
				ArrayList RPE=(ArrayList) this.reactions.get(key);
				ArrayList enzy = (ArrayList) RPE.get(0);
				ArrayList reac = (ArrayList)RPE.get(1);
				ArrayList prod = (ArrayList)RPE.get(2);
				
				String enz=(String) enzy.get(0);
				for (int i=1;i<enzy.size();i++){
					enz +=" & "+(String) enzy.get(i);
									
				}
				file+=("{\r\n ");
				file+=("'adjacencies': [ \r\n ");
				for (int i=0; i<reac.size();i++){
					file+=("{ \r\n");
					file+=("'nodeTo':"+"'"+reac.get(i)+ "', \r\n");
					file+=("'nodeFrom':"+"'" + enz +"', \r\n");
					file+=("'data':{} \r\n");
					file+=("}, \r\n");

				}
				
				file+=("], \r\n");
				file+=("'data': { \r\n");
				file+=("'$color': '#C74243', ");
				file+=("'$type': 'triangle', \r\n");
				file+=( " '$dim': 15 \r\n");
				file+=("}, \r\n");
				file+=("'id': "+"'"+enz+"',\r\n");
				file+=( "'name': "+"'"+enz+"'\r\n");
				file+=("},\r\n"); 
				
				/************************************/
				for (int i=0;i<reac.size();i++){
					file+=("{\r\n ");
					file+=("'adjacencies': [ ], \r\n ");
					//file+=(  "'"+enz+"'\r\n");
					//LISTE VIDE 				
					//file+=("], ");
					file+=("'data': { \r\n");
					file+=("'$color': '#416D9C', ");
					file+=("'$type': 'square', \r\n");
					file+=( " '$dim': 15 \r\n");
					file+=("}, \r\n");
					file+=("'id': "+"'"+reac.get(i)+"',\r\n");
					file+=( "'name': "+"'"+reac.get(i)+"'\r\n");
					file+=("},\r\n");
									
				}
				
				
				
				/////////////////////////////////////////
				file+=("{\r\n ");
				file+=("'adjacencies': [ \r\n ");
				for (int i=0; i<prod.size();i++){
					file+=("{ \r\n");
					file+=("'nodeTo':"+"'"+ prod.get(i)+ "', \r\n");
					file+=("'nodeFrom' :"+"'" + enz +"', \r\n");
					file+=("'data':{} \r\n");
					file+=("},\r\n");
				}
				file+=("], \r\n");
				file+=("'data': { \r\n");
				file+=("'$color': '#C74243', ");
				file+=("'$type': 'triangle', \r\n");
				file+=( " '$dim': 15 \r\n");
				file+=("}, \r\n");
				
				file+=("'id': "+"'"+enz+"',\r\n");
				file+=( "'name': "+"'"+enz+"'\r\n");
				file+=("},\r\n"); 
				
				
				/************************************/
				for (int i=0;i<prod.size();i++){
					file+=("{\r\n ");
					file+=("'adjacencies': [ ], \r\n ");
					//file+=(  "'"+enz+"'\r\n");				
					//file+=("], \r\n");
					file+=("'data': { \r\n");
					file+=("'$color': '#416D9C', ");
					file+=("'$type': 'square', \r\n");
					file+=( " '$dim': 15 \r\n");
					file+=("}, \r\n");
					file+=("'id': "+"'"+prod.get(i)+"',\r\n");
					file+=( "'name': "+"'"+prod.get(i)+"'\r\n");
					file+=("},\r\n");
									
				}
				
				
			}

		    
		    
		    
		    
		    file+=("  ];\r\n");
		    file+=("var fd = new $jit.ForceDirected({\r\n");
		    file+=("injectInto: 'infovis',\r\n");
		    file+=("Navigation: {\r\n");
		    file+=("enable: true,\r\n");
		    file+=("panning: 'avoid nodes',\r\n");
		    file+=("zooming: 10 \r\n");//zoom speed. higher is more sensible
		    file+=("  },\r\n");
		    file+=("Node: {\r\n");
		    file+=("overridable: true\r\n");
		    file+=("},\r\n");
		    file+=("Edge: {\r\n");
		    file+=("overridable: true,\r\n");
		    file+=("color: '#23A4FF',\r\n");
		    file+=("lineWidth: 0.4\r\n");
		    file+=("},\r\n");
		    file+=("Label: {\r\n");
		    file+=("type: labelType,\r\n"); //Native or HTML
		    file+=(" size: 10,\r\n");
	   	  	file+=("style: 'bold'\r\n");
	   	  	file+=("},\r\n");
	   	  	//Add Tips
	   	  	file+=(" Tips: {\r\n");
	   	  	file+=(" enable: true,\r\n");
	   	  	file+=(" onShow: function(tip, node) {\r\n");
			    //count connections
	   	  	file+=("  var count = 0;\r\n");
	   	  	file+=("node.eachAdjacency(function() { count++; });\r\n");
			    //display node info in tooltip
		  /*file+=("tip.innerHTML = '<div class=\'tip-title\'>' + node.name + '</div>'\r\n");
		  file+=("+ '<div class=\'tip-text\'><b>connections:</b> ' + count + '</div>';\r\n");*/
		  
		  
	   	  	file+=("tip.innerHTML = '<div class= \\'tip-title\\'>' + node.name + '</div>'+'<div class=\\'tip-text\\'><b>connections:</b> ' + count + '</div>';\r\n");
		
	   	  	file+=("}\r\n");
	   	  	file+=("},\r\n");
			  // Add node events
	   	  	file+=("Events: {\r\n");
		    file+=("enable: true,\r\n");
	        file+=("type: 'Native',\r\n");
				  //Change cursor style when hovering a node
	        file+=("onMouseEnter: function() {\r\n");
	        file+=("fd.canvas.getElement().style.cursor = 'move';\r\n");
	        file+=("},\r\n");
	        file+=("onMouseLeave: function() {\r\n");
	        file+=("fd.canvas.getElement().style.cursor = '';\r\n");
	        file+=("},\r\n");
				  //Update node positions when dragged
	        		
	        file+=("onDragMove: function(node, eventInfo, e) {\r\n");
			file+=("var pos = eventInfo.getPos();\r\n");
			file+=("node.pos.setc(pos.x, pos.y);\r\n");
		    file+=("fd.plot();\r\n");
			file+=(" },\r\n");
				  //Implement the same handler for touchscreens
			file+=("onTouchMove: function(node, eventInfo, e) {\r\n");
			file+=("$jit.util.event.stop(e); \r\n");//stop default touchmove event
			file+=(" this.onDragMove(node, eventInfo, e);\r\n");
			file+=("},\r\n");
				  //Add also a click handler to nodes
			file+=("onClick: function(node) {\r\n");
			file+=("if(!node) return;\r\n");
				    // Build the right column relations list.
				    // This is done by traversing the clicked node connections.
			file+=(" var html = '<h4>' + node.name + '</h4><b> connections:</b><ul><li>',\r\n");
			file+=(" list = [];\r\n");
			file+=("node.eachAdjacency(function(adj){\r\n");
			file+=(" list.push(adj.nodeTo.name);\r\n");
			file+=(" });\r\n");
				    //append connections information
			file+=("$jit.id('inner-details').innerHTML = html + list.join('</li><li>') + '</li></ul>';\r\n");
			file+=("}\r\n");
			file+=("},\r\n");
				  //Number of iterations for the FD algorithm
			file+=("iterations: 200,\r\n");
				  //Edge length
			file+=("levelDistance: 130,\r\n");
				  // Add text to the labels. This method is only triggered
				  // on label creation and only for DOM labels (not native canvas ones).
			file+=("onCreateLabel: function(domElement, node){\r\n");
			file+=("domElement.innerHTML = node.name;\r\n");
			file+=("var style = domElement.style;\r\n");
			file+=("style.fontSize = '0.8em';\r\n");
		    file+=("style.color = '#ddd';\r\n");
			file+=("},\r\n");
				  // Change node styles when DOM labels are placed
				  // or moved.
		    file+=("onPlaceLabel: function(domElement, node){\r\n");
			file+=("var style = domElement.style;\r\n");
			file+=("var left = parseInt(style.left);\r\n");
			file+=("var top = parseInt(style.top);\r\n");
			file+=("var w = domElement.offsetWidth;\r\n");
			file+=("style.left = (left - w / 2) + 'px';\r\n");
			file+=("style.top = (top + 10) + 'px';\r\n");
			file+=("style.display = '';\r\n");
			file+=("}\r\n");
			file+=("});\r\n");
				  // load JSON data.
			file+=("fd.loadJSON(json);\r\n");
				  // compute positions incrementally and animate.
			file+=("fd.computeIncremental({\r\n");
			file+=("iter: 40,\r\n");
			file+=("property: 'end',\r\n");
			file+=("onStep: function(perc){\r\n");
			file+=("Log.write(perc + '% loaded...');\r\n");
			file+=("},\r\n");
			file+=("onComplete: function(){\r\n");
			file+=("Log.write('done');\r\n");
			file+=("fd.animate({\r\n");
		    file+=("modes: ['linear'],\r\n");
			file+=("transition: $jit.Trans.Elastic.easeOut,\r\n");
			file+=("duration: 2500\r\n");
			file+=("});\r\n");
			file+=("}\r\n");
			file+=("});\r\n");
				  // end
			file+=("}\r\n");
		  return file;
		}
		
	

}
