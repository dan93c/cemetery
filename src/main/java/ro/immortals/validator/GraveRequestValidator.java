package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.GraveRequest;

public class GraveRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return GraveRequest.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nrInfocet",
				"error.graveRequest.nrInfocet.required");

		GraveRequest graveRequest = (GraveRequest) target;

		if (graveRequest.getNrInfocet().length() >= 45) {
			errors.rejectValue("nrInfocet", "longText",
					new Object[] { "Nr.Infocet" },
					"Campul 'Nr.Infocet' este prea lung.");
		}

		if (graveRequest.getNrInfocet() != null
				&& !graveRequest.getNrInfocet().isEmpty()) {
			if (Character.isWhitespace(graveRequest.getNrInfocet().charAt(0))) {
				errors.rejectValue("nrInfocet", "whitespace",
						new Object[] { "Nr.Infocet" },
						"Campul 'Nr.Infocet' nu poate incepe cu spatiu.");
			}
		}

		if (graveRequest.getSolvingStage().length() >= 45) {
			errors.rejectValue("solvingStage", "longText",
					new Object[] { "Stadiu de solutionare" },
					"Campul 'Stadiu de solutionare' este prea lung.");
		}

	}
}
