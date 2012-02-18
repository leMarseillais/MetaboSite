package org.metabosite.module.controllers.user;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.validator.RegexValidator;

import org.metabosite.utils.Bundle;

public class MailValidator extends RegexValidator {

	public void validate(javax.faces.context.FacesContext context,
			javax.faces.component.UIComponent component, java.lang.Object value) {

		Pattern p = Pattern
				.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");

		UIInput mail = (UIInput) component;
		String valueStr = (String) value;
		if (!p.matcher(valueStr).matches()) {
			mail.setValid(false);

			String err = Bundle.Err.getBundle().getString("mailError");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, err, err);
			context.addMessage(component.getClientId(), message);
		} else {
			mail.setValid(true);
		}
	}
}
