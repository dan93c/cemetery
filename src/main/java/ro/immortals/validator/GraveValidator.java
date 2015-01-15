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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nrGrave",
				"error.grave.nrGrave.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type",
				"error.grave.type.required");

		Grave grave = (Grave) target;

		if (grave.getNrGrave().length() >= 45) {
			errors.rejectValue("nrGrave", "longText",
					new Object[] { "Nr.Mormant" },
					"Campul 'Nr.Mormant' este prea lung.");
		}

		if (grave.getNrGrave() != null && !grave.getNrGrave().isEmpty()) {
			if (Character.isWhitespace(grave.getNrGrave().charAt(0))) {
				errors.rejectValue("nrGrave", "whitespace",
						new Object[] { "Nr.Mormant" },
						"Campul 'Nr.Mormant' nu poate incepe cu spatiu.");
			}
		}

		if (grave.getObservations().length() >= 100) {
			errors.rejectValue("observations", "longText",
					new Object[] { "Observatii" },
					"Campul 'Observatii' este prea lung.");
		}

		if (grave.getObservations() != null
				&& !grave.getObservations().isEmpty()) {
			if (Character.isWhitespace(grave.getObservations().charAt(0))) {
				errors.rejectValue("observations", "whitespace",
						new Object[] { "Observatii" },
						"Campul 'Observatii' nu poate incepe cu spatiu.");
			}
		}

		if (grave.getType().length() >= 45) {
			errors.rejectValue("type", "longText",
					new Object[] { "Tip.Mormant" },
					"Campul 'Tip.Mormant' este prea lung.");
		}

		if (grave.getType() != null && !grave.getType().isEmpty()) {
			if (Character.isWhitespace(grave.getType().charAt(0))) {
				errors.rejectValue("type", "whitespace",
						new Object[] { "Tip.Mormant" },
						"Campul 'Tip.Mormant' nu poate incepe cu spatiu.");
			}
		}

		if (grave.getSurface().length() >= 45) {
			errors.rejectValue("surface", "longText",
					new Object[] { "Suprafata" },
					"Campul 'Suprafata' este prea lung.");
		}

		if (grave.getSurface() != null && !grave.getSurface().isEmpty()) {
			if (Character.isWhitespace(grave.getSurface().charAt(0))) {
				errors.rejectValue("surface", "whitespace",
						new Object[] { "Suprafata" },
						"Campul 'Suprafata' nu poate incepe cu spatiu.");
			}
		}
	}

}
