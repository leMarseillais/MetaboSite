package org.metabosite.ui.tags;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class ModuleMenuItem extends UIComponentBase {
	
	private String name = "";
	private String href = "";
	
	public String getHref() {
		ve = getValueExpression("href");
		if (ve != null) {
			href = (String) ve.getValue(
					getFacesContext().getELContext());
		}
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	private ValueExpression ve;
	
	public String getName() {
		ve = getValueExpression("name");
		if (ve != null) {
			name = (String) ve.getValue(
					getFacesContext().getELContext());
		}
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("a", this);
		writer.writeAttribute("href", href, "href");
		writer.writeText(name, "name");
		writer.endElement("a");
		
		super.encodeBegin(context);
	}

	@Override
	public String getFamily() {
		return "ModuleMenuItem";
	}

}
