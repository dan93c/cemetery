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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"error.dead.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname",
				"error.dead.surname.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnp",
				"error.dead.cnp.required");

		Dead dead = (Dead) target;

		if (dead.getReligion().length() >= 45) {
			errors.rejectValue("religion", "longText",
					new Object[] { "Religion" },
					"Campul 'Religion' este prea lung.");
		}

		if (dead.getName().length() >= 45) {
			errors.rejectValue("name", "longText", new Object[] { "Nume" },
					"Campul 'Nume' este prea lung.");
		}
		if (dead.getSurname().length() >= 45) {
			errors.rejectValue("surname", "longText",
					new Object[] { "Surname" },
					"Campul 'Surname' este prea lung.");
		}

		if (dead.getCnp().length() >= 15) {
			errors.rejectValue("cnp", "longText", new Object[] { "Cnp" },
					"Campul 'Cnp' este prea lung.");
		}
	}

}
