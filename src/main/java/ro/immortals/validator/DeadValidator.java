package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.Dead;

public class DeadValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Dead.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"error.dead.firstName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"error.dead.lastName.required");

		Dead dead = (Dead) target;

		if (dead.getReligion().length() >= 45) {
			errors.rejectValue("religion", "longText",
					new Object[] { "Religie" },
					"Campul 'Religie' este prea lung.");
		}

		if (dead.getFirstName().length() >= 100) {
			errors.rejectValue("firstName", "longText", new Object[] { "Nume" },
					"Campul 'Nume' este prea lung.");
		}
		if (dead.getLastName().length() >= 100) {
			errors.rejectValue("lastName", "longText",
					new Object[] { "Prenume" },
					"Campul 'Prenume' este prea lung.");
		}
		
	}

}
