package ro.immortals.validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receipt_nr", "error.concessionContract.receipt_nr.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnp", "error.concessionContract.cnp.required");

		ConcessionContract concessionContract = (ConcessionContract) target;

		if (isValidEmailAddress(concessionContract.getEmailAddress()) == false
		        && concessionContract.getEmailAddress() != null) {
			errors.rejectValue("emailAddress", "error.concessionContract.email.invalid", null,
			        "Adresa de email este invalida");
		} else {
			if (concessionContract.getReceiptNr().length() >= 45) {
				errors.rejectValue("receipt_nr", "longText", new Object[] { "Receipt_Nr" },
				        "Campul 'Receipt_Nr' este prea lung.");
			}
			if (concessionContract.getAddress().length() >= 100) {
				errors.rejectValue("address", "longText", new Object[] { "Adresa" }, "Campul 'Adresa' este prea lung.");
			}
			if (concessionContract.getFirstName().length() >= 100) {
				errors.rejectValue("firstName", "longText", new Object[] { "Nume" }, "Campul 'Nume' este prea lung.");
			}
			if (concessionContract.getLastName().length() >= 100) {
				errors.rejectValue("lastName", "longText", new Object[] { "Prenume" },
				        "Campul 'Prenume' este prea lung.");
			}

			if (concessionContract.getCnp().length() >= 15) {
				errors.rejectValue("cnp", "longText", new Object[] { "Cnp" }, "Campul 'Cnp' este prea lung.");
			}

			if (concessionContract.getEmailAddress().length() >= 100) {
				errors.rejectValue("emailAddress", "longText", new Object[] { "Email" },
				        "Campul 'Email' este prea lung.");
			}
		}
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}
