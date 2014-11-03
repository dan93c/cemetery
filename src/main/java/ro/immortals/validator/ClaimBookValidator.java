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
		ValidationUtils.rejectIfEmpty(errors, "id",
				"error.claimBook.id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "claims",
				"error.claimBook.claims.required");

		ClaimBook claimBook = (ClaimBook) target;

		if (claimBook.getId() < 0) {
			errors.rejectValue("id", "negativeValue", new Object[] { "Id" },
					"Campul 'Id' este negativ.");
		}

		if (claimBook.getClaims().length() >= 1000) {
			errors.rejectValue("claims", "longText", new Object[] { "Claims" },
					"Campul 'Claims' este prea lung.");
		}

	}

}
