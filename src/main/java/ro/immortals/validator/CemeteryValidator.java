package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.Cemetery;

public class CemeteryValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Cemetery.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "error.cemetery.code.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.cemetery.name.required");

		Cemetery cemetery = (Cemetery) target;

		if (cemetery.getCode().length() >= 45) {
			errors.rejectValue("code", "longText", new Object[] { "Cod" }, "Campul 'Cod' este prea lung.");
		}
		if (cemetery.getName().length() >= 100) {
			errors.rejectValue("name", "longText", new Object[] { "Nume" }, "Campul 'Nume' este prea lung.");
		}
		if (cemetery.getAddress().length() >= 100) {
			errors.rejectValue("address", "longText", new Object[] { "Adresa" }, "Campul 'Adresa' este prea lung.");
		}
	}

}
