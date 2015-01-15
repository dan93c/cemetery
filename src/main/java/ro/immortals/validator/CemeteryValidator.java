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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"error.cemetery.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adress",
				"error.cemetery.adress.required");
		
		Cemetery cemetery = (Cemetery) target;

		if (cemetery.getName().length() >= 100) {
			errors.rejectValue("name", "longText", new Object[] { "Nume" },
					"Campul 'Nume' este prea lung.");
		}
		if (cemetery.getAddress().length() >= 100) {
			errors.rejectValue("address", "longText",
					new Object[] { "Adresa" },
					"Campul 'Adresa' este prea lung.");
		}

		if (Character.isWhitespace(cemetery.getAddress().charAt(0))) {
			errors.rejectValue("address", "whitespace",
					new Object[] { "Adresa" },
					"Campul 'Adresa' nu poate incepe cu spatiu.");
		}

		if (Character.isWhitespace(cemetery.getName().charAt(0))) {
			errors.rejectValue("name", "whitespace", new Object[] { "Nume" },
					"Campul 'Nume' nu poate incepe cu spatiu.");
		}
	}

}
