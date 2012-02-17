package org.metabosite.ui.tags;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class ModuleSpace extends UniqTag {
	
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
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("h1", this);
		writer.write(getTitle());
		writer.endElement("h1");
		
		writer.startElement("div", this);
		
		super.encodeBegin(context);
	}
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("div");
		
		super.encodeEnd(context);
	}

	@Override
	public String getFamily() {
		return "ModuleSpace";
	}
	
}
