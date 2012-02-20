package org.metabosite.ui.tags;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.metabosite.module.utils.Bundle;
import org.metabosite.module.utils.Global;

import com.sun.istack.logging.Logger;

public abstract class UniqTag extends UIComponentBase {

	private final String MODULE_SPACE_CREATED = "MODULE_SPACE_CREATED";
	private final Logger logger = Logger.getLogger(ModuleSpace.class);
	private final ResourceBundle bundle = Bundle.UI.getBundle();

	private boolean moduleSpaceCreated(FacesContext context) {
		Boolean msc = (Boolean) context.getAttributes().get(
				MODULE_SPACE_CREATED + "_" + getFamily());
		if (msc != null)
			return msc;
		else
			return false;
	}

	public void moduleSpaceCreation(FacesContext context) {
		context.getAttributes().put(MODULE_SPACE_CREATED + "_" + getFamily(),
				true);
	}

	@Override
	public void encodeAll(FacesContext context) throws IOException {
		if (moduleSpaceCreated(context)) {
			logger.log(Level.WARNING,
					bundle.getString("WARNING_MULTIPLE_MODSPACE") + " ("
							+ getFamily() + ")");
			return;
		} else {
			moduleSpaceCreation(context);

			ResponseWriter writer = context.getResponseWriter();
			writer.startElement("link", this);
			writer.writeAttribute("rel", "stylesheet", null);
			writer.writeAttribute("type", "text/css", null);
			writer.writeAttribute("href", Global.resourcePath("org.metabosite/" + getFamily() + ".css"), null);
			writer.endElement("link");
			
			writer.startElement("div", this);
			writer.writeAttribute("id", getFamily(), null);
			
			encodeBegin(context);
			encodeChildren(context);
			encodeEnd(context);
			
			writer.endElement("div");
		}
	}

}
