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
		ValidationUtils.rejectIfEmpty(errors, "id",
				"error.plot.id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surface",
				"error.plot.surface.required");

		Plot plot = (Plot) target;

		if (plot.getName().length() >= 45) {
			errors.rejectValue("name", "longText",
					new Object[] { "Name" },
					"Campul 'Name' este prea lung.");
		}

		if (plot.getSurface().length() >= 45) {
			errors.rejectValue("surface", "longText", new Object[] { "Surface" },
					"Campul 'Surface' este prea lung.");
		}
		
	
		if (plot.getId() < 0) {
			errors.rejectValue("id", "negativeValue", new Object[] { "Id" },
					"Campul 'Id' este negativ.");
		}
	}

}
