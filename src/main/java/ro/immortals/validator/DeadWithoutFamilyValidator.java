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
					"Campul 'Adeverin�a de �nhumare' este prea lung.");
		}

		if (Character.isWhitespace(deadWithoutFamily.getFuneralCertificate()
				.charAt(0))) {
			errors.rejectValue("funeralCertificate", "whitespace",
					new Object[] { "Funeral_Certificate" },
					"Campul 'Adeverin�a de �nhumare' nu poate incepe cu spatiu.");
		}

		if (deadWithoutFamily.getImlRequest().length() >= 50) {
			errors.rejectValue("imlRequest", "longText",
					new Object[] { "Iml_Request" },
					"Campul 'Solicitare IML' este prea lung.");
		}
		
		if (Character.isWhitespace(deadWithoutFamily.getImlRequest().charAt(0))) {
			errors.rejectValue("imlRequest", "whitespace",
					new Object[] { "Iml_Request" },
					"Campul 'Solicitare IML' nu poate incepe cu spatiu.");
		}
	}

}
