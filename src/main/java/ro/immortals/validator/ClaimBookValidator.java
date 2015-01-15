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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "complainer",
				"error.claimBook.complainer.required");

		ClaimBook claimBook = (ClaimBook) target;

		if (claimBook.getComplainer().length() >= 100) {
			errors.rejectValue("complainer", "longText",
					new Object[] { "Reclamant" },
					"Campul 'Reclamant' este prea lung.");
		}

		if (claimBook.getComplainer() != null
				&& !claimBook.getComplainer().isEmpty()) {
			if (Character.isWhitespace(claimBook.getComplainer().charAt(0))) {
				errors.rejectValue("complainer", "whitespace",
						new Object[] { "Reclamant" },
						"Campul 'Reclamant' nu poate incepe cu spatiu.");
			}
		} else {
			errors.rejectValue("complainer", "erro.claimBook.complainer.null",
					new Object[] { "Reclamant" },
					"Campul 'Reclamant' nu poate ramane necompletat.");
		}

		if (claimBook.getClaims().length() >= 500) {
			errors.rejectValue("claims", "longText",
					new Object[] { "Reclamatii" },
					"Campul 'Reclamatii' este prea lung.");
		}

		if (claimBook.getClaims() != null && !claimBook.getClaims().isEmpty()) {
			if (Character.isWhitespace(claimBook.getClaims().charAt(0))) {
				errors.rejectValue("claims", "whitespace",
						new Object[] { "Reclamatii" },
						"Campul 'Reclamatii' nu poate incepe cu spatiu.");
			}
		} else {
			errors.rejectValue("claims", "erro.claimBook.claims.null",
					new Object[] { "Reclamatii" },
					"Campul 'Reclamatii' nu poate ramane necompletat.");
		}
	}

}
