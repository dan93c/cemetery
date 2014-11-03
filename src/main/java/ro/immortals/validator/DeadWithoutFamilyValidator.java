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
		ValidationUtils.rejectIfEmpty(errors, "id",
				"error.deadWithoutFamily.id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funeralCertificate",
				"error.deadWithoutFamily.funeralCertificate.required");
		

		DeadWithoutFamily deadWithoutFamily = (DeadWithoutFamily) target;

		if (deadWithoutFamily.getFeneralCertificate().length() >= 50) {
			errors.rejectValue("funeralCertificate", "longText",
					new Object[] { "Funeral_Certificate" },
					"Campul 'Funeral_Certificate' este prea lung.");
		}

		if (deadWithoutFamily.getImlRequest().length() >= 50) {
			errors.rejectValue("imlRequest", "longText", new Object[] { "Iml_Request" },
					"Campul 'Iml_Request' este prea lung.");
		}
		if (deadWithoutFamily.getId() < 0) {
			errors.rejectValue("id", "negativeNumber", new Object[] { "Id" },
					"Campul 'Id' este negativ.");
		}
	}

}
