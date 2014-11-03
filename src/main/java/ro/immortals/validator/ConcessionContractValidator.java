package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.ConcessionContract;

public class ConcessionContractValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return ConcessionContract.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "current_nrr",
				"error.concessionContract.current_nr.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receipt_nr",
				"error.concessionContract.receipt_nr.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnp",
				"error.concessionContract.cnp.required");

		ConcessionContract concessionContract = (ConcessionContract) target;

		if (concessionContract.getCurrentNr().length() >= 45) {
			errors.rejectValue("current_nr", "longText",
					new Object[] { "Current_Nr" },
					"Campul 'Current_Nr' este prea lung.");
		}
		if (concessionContract.getReceiptNr().length() >= 45) {
			errors.rejectValue("receipt_nr", "longText",
					new Object[] { "Receipt_Nr" },
					"Campul 'Receipt_Nr' este prea lung.");
		}
		if (concessionContract.getAddress().length() >= 100) {
			errors.rejectValue("address", "longText",
					new Object[] { "Adresa" },
					"Campul 'Adresa' este prea lung.");
		}
		if (concessionContract.getName().length() >= 45) {
			errors.rejectValue("name", "longText", new Object[] { "Nume" },
					"Campul 'Nume' este prea lung.");
		}
		if (concessionContract.getSurname().length() >= 45) {
			errors.rejectValue("surname", "longText",
					new Object[] { "Surname" },
					"Campul 'Surname' este prea lung.");
		}

		if (concessionContract.getCnp().length() >= 15) {
			errors.rejectValue("cnp", "longText", new Object[] { "Cnp" },
					"Campul 'Cnp' este prea lung.");
		}
	}

}
