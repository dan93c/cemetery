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

		Plot plot = (Plot) target;

		if (plot.getName().length() >= 45) {
			errors.rejectValue("name", "longText", new Object[] { "Nume" },
					"Campul 'Nume' este prea lung.");
		}

		if (plot.getName() != null && !plot.getName().isEmpty()) {
			if (Character.isWhitespace(plot.getName().charAt(0))) {
				errors.rejectValue("name", "whitespace",
						new Object[] { "Nume" },
						"Campul 'Nume' nu poate incepe cu spatiu.");
			}
		}

	}

}
