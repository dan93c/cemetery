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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentNr",
				"error.graveRequest.currentNr.required");

		GraveRequest graveRequest = (GraveRequest) target;

		if (graveRequest.getCurrentNr().length() >= 45) {
			errors.rejectValue("currentNr", "longText",
					new Object[] { "Current_Nr" },
					"Campul 'Current_Nr' este prea lung.");
		}

		if (graveRequest.getNrInfocet().length() >= 45) {
			errors.rejectValue("nrInfocet", "longText",
					new Object[] { "Nr_Infocet" },
					"Campul 'Nr_Infocet' este prea lung.");
		}

		if (graveRequest.getSolvingStage().length() >= 45) {
			errors.rejectValue("solvingStage", "longText",
					new Object[] { "Solving_Stage" },
					"Campul 'Solving_Stage' este prea lung.");
		}

	}
}
