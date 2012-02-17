package org.metabosite.ui.tags;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.metabosite.utils.Bundle;

import com.sun.istack.logging.Logger;

public class ModuleSpace extends UIComponentBase {
	
	private final Boolean MODULE_SPACE_CREATED = false;
	private final Logger logger = Logger.getLogger(ModuleSpace.class);
	private final ResourceBundle bundle = Bundle.UI.getBundle();
	
	private String title;
	
	private ValueExpression ve;
	
	public String getTitle() {
		ve = getValueExpression("title");
		if (ve != null) {
			title = (String) ve.getValue(
					getFacesContext().getELContext());
		}
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private boolean moduleSpaceCreated(FacesContext context) {
		Boolean msc = (Boolean) context.getAttributes().get(MODULE_SPACE_CREATED);
		if (msc != null)
			return msc;
		else
			return false;
	}
	
	public void moduleSpaceCreation(FacesContext context) {
		context.getAttributes().put(MODULE_SPACE_CREATED, true);
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("div", this);
		
		writer.startElement("h1", this);
		writer.write(getTitle());
		writer.endElement("h1");
		
		writer.startElement("div", this);
		
		super.encodeBegin(context);
	}
	
	@Override
	public void encodeAll(FacesContext context) throws IOException {
		if (moduleSpaceCreated(context)) {
			logger.log(Level.WARNING, bundle.getString("WARNING_MULTIPLE_MODSPACE"));
			return;
		}
		else {
			moduleSpaceCreation(context);
			super.encodeAll(context);
		}
	}
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("div");
		writer.endElement("div");
		
		super.encodeEnd(context);
	}

	@Override
	public String getFamily() {
		return "ModuleSpace";
	}
	
}
