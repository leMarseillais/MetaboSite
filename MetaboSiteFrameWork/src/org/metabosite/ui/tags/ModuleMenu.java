package org.metabosite.ui.tags;

import java.io.IOException;
import java.util.Iterator;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.metabosite.model.Item;
import org.metabosite.model.ItemList;

public class ModuleMenu extends UniqTag {
	
	private ItemList items;
	private ValueExpression ve;
	
	public ItemList getItems() {
		ve = getValueExpression("items");
		if (ve != null) {
			items = (ItemList) ve.getValue(
					getFacesContext().getELContext());
		}
		return this.items;
	}

	public void setItems(ItemList items) {
		this.items = items;
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		for (Iterator<Item> it = getItems().iterator(); it.hasNext();) {
			Item i = it.next();

			writer.startElement("a", this);
			writer.writeAttribute("href", i.getHref(), "href");
			writer.writeText(i.getName(), "name");
			writer.endElement("a");
		}
		
		super.encodeBegin(context);
	}
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		super.encodeEnd(context);
	}

	@Override
	public String getFamily() {
		return "ModuleMenu";
	}

}
