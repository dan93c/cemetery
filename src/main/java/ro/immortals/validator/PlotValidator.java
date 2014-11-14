package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.Plot;

public class PlotValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Plot.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"error.plot.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surface",
				"error.plot.surface.required");

		Plot plot = (Plot) target;

		if (plot.getName().length() >= 45) {
			errors.rejectValue("name", "longText",
					new Object[] { "Denumire" },
					"Campul 'Denumire' este prea lung.");
		}

		if (plot.getSurface().length() >= 45) {
			errors.rejectValue("surface", "longText", new Object[] { "Suprafata" },
					"Campul 'Suprafata' este prea lung.");
		}
		
	
		if (plot.getId() <= 0) {
			errors.rejectValue("surface", "negativeValue", new Object[] { "Suprafata" },
					"Suprafata nu poate fi negativa.");
		}
	}

}
