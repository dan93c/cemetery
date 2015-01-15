package ro.immortals.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return User.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
				"error.user.username.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"error.user.password.required");

		User user = (User) target;

		if (user.getUsername().length() >= 45) {
			errors.rejectValue("username", "longText",
					new Object[] { "Username" },
					"Campul 'Username' este prea lung.");
		}

		if (Character.isWhitespace(user.getUsername().charAt(0))) {
			errors.rejectValue("username", "whitespace",
					new Object[] { "Username" },
					"Campul 'Username' nu poate incepe cu spatiu.");
		}

		if (user.getPassword().length() >= 45) {
			errors.rejectValue("password", "longText",
					new Object[] { "Parola" },
					"Campul 'Parola' este prea lung.");
		}

		if (Character.isWhitespace(user.getPassword().charAt(0))) {
			errors.rejectValue("password", "whitespace",
					new Object[] { "Parola" },
					"Campul 'Parola' nu poate incepe cu spatiu.");
		}

	}

}
