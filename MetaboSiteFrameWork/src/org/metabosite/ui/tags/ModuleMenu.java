package org.metabosite.ui.tags;

import java.io.IOException;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class ModuleMenu extends UIComponentBase {
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", this);
		
		super.encodeBegin(context);
	}
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);

		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("div");
	}

	@Override
	public String getFamily() {
		return "ModuleMenu";
	}

}
