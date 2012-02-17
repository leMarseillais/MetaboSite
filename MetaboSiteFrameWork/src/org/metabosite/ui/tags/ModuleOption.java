package org.metabosite.ui.tags;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class ModuleOption extends UIComponentBase {
	
	private String name = "";
	private String value = "";
	private String id = null;
	
	public String getId() {
		ve = getValueExpression("id");
		if (ve != null) {
			id = (String) ve.getValue(
					getFacesContext().getELContext());
		}
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getValue() {
		ve = getValueExpression("value");
		if (ve != null) {
			value = (String) ve.getValue(
					getFacesContext().getELContext());
		}
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
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
		writer.startElement("div", this);
		if (getId() != null)
			writer.writeAttribute("id", getId(), "id");
		
		writer.startElement("span", this);
		writer.writeAttribute("class", "optionKey", null);
		writer.writeText(getName(), "name");
		writer.endElement("span");
		
		writer.startElement("span", this);
		writer.writeAttribute("class", "optionValue", null);
		writer.writeText(getValue(), "value");
		writer.endElement("span");
		
		writer.endElement("div");
		
		super.encodeBegin(context);
	}

	@Override
	public String getFamily() {
		return "ModuleOption";
	}

}
