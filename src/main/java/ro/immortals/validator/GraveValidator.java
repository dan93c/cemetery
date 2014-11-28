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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surface",
				"error.grave.surface.required");
		
		Grave grave = (Grave) target;

		if (grave.getNrGrave().length() >= 45) {
			errors.rejectValue("nrGrave", "longText", new Object[] { "Nr.Mormant" },
			        "Campul 'Nr.Mormant' este prea lung.");
		}

		if (grave.getObservations().length() >= 100) {
			errors.rejectValue("observations", "longText", new Object[] { "Observatii" },
			        "Campul 'Observatii' este prea lung.");
		}

		if (grave.getType().length() >= 45) {
			errors.rejectValue("type", "longText", new Object[] { "Tip.Mormant" },
			        "Campul 'Tip.Mormant' este prea lung.");
		}

		if (grave.getSurface().length() >= 45) {
			errors.rejectValue("surface", "longText", new Object[] { "Suprafata" },
			        "Campul 'Suprafata' este prea lung.");
		}
	}

}
