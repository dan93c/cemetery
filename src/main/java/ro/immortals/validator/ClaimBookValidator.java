package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.ClaimBook;

public class ClaimBookValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return ClaimBook.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "claims",
				"error.claimBook.claims.required");

		ClaimBook claimBook = (ClaimBook) target;

		if (claimBook.getComplainer().length() >= 100) {
			errors.rejectValue("complainer", "longText", new Object[] { "Reclamant" },
					"Campul 'Reclamant' este prea lung.");
		}
		if (claimBook.getClaims().length() >= 500) {
			errors.rejectValue("claims", "longText", new Object[] { "Reclamatii" },
					"Campul 'Reclamatii' este prea lung.");
		}

	}

}
