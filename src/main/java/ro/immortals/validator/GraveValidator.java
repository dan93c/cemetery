package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.Grave;

public class GraveValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Grave.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "id", "error.grave.id.required");

		Grave grave = (Grave) target;

		if (grave.getNrGrave().length() >= 45) {
			errors.rejectValue("nrGrave", "longText",
					new Object[] { "Nr_Grave" },
					"Campul 'Nr_Grave' este prea lung.");
		}

		if (grave.getObservations().length() >= 45) {
			errors.rejectValue("observations", "longText",
					new Object[] { "Observations" },
					"Campul 'Observations' este prea lung.");
		}

		if (grave.getGraveType().length() >= 45) {
			errors.rejectValue("graveType", "longText",
					new Object[] { "Grave_Type" },
					"Campul 'Grave_Type' este prea lung.");
		}
		if (grave.getId() < 0) {
			errors.rejectValue("id", "negativeValue", new Object[] { "Id" },
					"Campul 'Id' este negativ.");
		}
	}

}
