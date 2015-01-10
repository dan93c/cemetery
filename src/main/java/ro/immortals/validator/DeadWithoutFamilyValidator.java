package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.DeadWithoutFamily;

public class DeadWithoutFamilyValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return DeadWithoutFamily.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funeralCertificate",
				"error.deadWithoutFamily.funeralCertificate.required");
		

		DeadWithoutFamily deadWithoutFamily = (DeadWithoutFamily) target;

		if (deadWithoutFamily.getFuneralCertificate().length() >= 50) {
			errors.rejectValue("funeralCertificate", "longText",
					new Object[] { "Funeral_Certificate" },
					"Campul 'Adeverinþa de înhumare' este prea lung.");
		}

		if (deadWithoutFamily.getImlRequest().length() >= 50) {
			errors.rejectValue("imlRequest", "longText", new Object[] { "Iml_Request" },
					"Campul 'Solicitare IML' este prea lung.");
		}
	}

}
