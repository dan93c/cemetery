package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.History;

public class HistoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return History.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actionName", "error.history.actionName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modifiedObjectCode",
		        "error.history.modifiedObjectCode.required");

		History history = (History) target;

		if (history.getActionName().length() >= 100) {
			errors.rejectValue("actionName", "longText", new Object[] { "Nume.Actiune" },
			        "Campul 'Nume.Actiune' este prea lung.");
		}

		if (history.getModifiedObjectCode().length() >= 500) {
			errors.rejectValue("modifiedObjectCode", "longText", new Object[] { "Obiect.Modificat" },
			        "Campul 'Obiect.Modificat' este prea lung.");
		}

		if (history.getDetails().length() >= 500) {
			errors.rejectValue("details", "longText", new Object[] { "Detalii" }, "Campul 'Detalii' este prea lung.");
		}
	}

}
