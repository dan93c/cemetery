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
		ValidationUtils
				.rejectIfEmpty(errors, "id", "error.history.id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actionName",
				"error.history.actionName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modifiedObjectCode",
				"error.history.modifiedObjectCode.required");

		History history = (History) target;

		if (history.getActionName().length() >= 100) {
			errors.rejectValue("actionName", "longText",
					new Object[] { "Action_Name" },
					"Campul 'Action_Name' este prea lung.");
		}

		if (history.getModifiedObjectCode().length() >= 1000) {
			errors.rejectValue("modifiedObjectCode", "longText",
					new Object[] { "Modified_Object_Code" },
					"Campul 'Modified_Object_Code' este prea lung.");
		}

		if (history.getDetails().length() >= 100) {
			errors.rejectValue("details", "longText",
					new Object[] { "Details" },
					"Campul 'Details' este prea lung.");
		}
		if (history.getId() < 0) {
			errors.rejectValue("id", "negativeValue", new Object[] { "Id" },
					"Campul 'Id' este negativ.");
		}
	}

}
