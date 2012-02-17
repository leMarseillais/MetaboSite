package org.metabosite.ui.tags;

import java.io.IOException;
import java.util.Iterator;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.metabosite.model.Option;
import org.metabosite.model.OptionList;

public class ModuleOptions extends UniqTag {
	
	private OptionList options = new OptionList();
	private ValueExpression ve;
	
	public OptionList getOptions() {
		ve = getValueExpression("options");
		if (ve != null) {
			options = (OptionList) ve.getValue(
					getFacesContext().getELContext());
		}
		return this.options;
	}

	public void setOptions(OptionList options) {
		this.options = options;
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		for (Iterator<Option> it = getOptions().iterator(); it.hasNext();) {
			Option o = it.next();

			writer.startElement("div", this);
			
			writer.startElement("span", this);
			writer.writeAttribute("class", "optionKey", null);
			writer.writeText(o.getName(), "name");
			writer.endElement("span");
			
			writer.startElement("span", this);
			writer.writeAttribute("class", "optionValue", null);
			writer.writeText(o.getValue(), "value");
			writer.endElement("span");
			
			writer.endElement("div");
		}
		
		super.encodeBegin(context);
	}
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);
	}

	@Override
	public String getFamily() {
		return "ModuleOptions";
	}
}
