package ro.immortals.validator;

import java.util.Calendar;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.Dead;

public class DeadValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Dead.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.field.required", new Object[] { "Nume" },
		        "Numele este necesar");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.field.required",
		        new Object[] { "Prenume" }, "Prenumele este necesar");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deathDate", "error.field.required",
		        new Object[] { "Data mortii" }, "Data mortii este necesara");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funeralDate", "error.field.required",
		        new Object[] { "Data inmormantarii" }, "Data inmormantarii este necesara");	// nu stiu ce sa zic de asta..

		Dead dead = (Dead) target;

		if (dead.getReligion().length() >= 45) {
			errors.rejectValue("religion", "longText", new Object[] { "Religie" }, "Campul 'Religie' este prea lung.");
		}

		if (dead.getFirstName().length() >= 100) {
			errors.rejectValue("firstName", "longText", new Object[] { "Nume" }, "Campul 'Nume' este prea lung.");
		}
		if (dead.getLastName().length() >= 100) {
			errors.rejectValue("lastName", "longText", new Object[] { "Prenume" }, "Campul 'Prenume' este prea lung.");
		}

		if (dead.getFuneralDate() != null) {
			if (dead.getDeathDate() == null) {
				errors.rejectValue("deathDate", "error.field.required", new Object[] { "Data mortii" },
				        "Data mortii este necesara");
			} else {
				System.out.println("2---------" + dead.getFuneralDate().toString());
				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance();
				calendar1.setTime(new Date());
				calendar2.setTime(dead.getFuneralDate());
				System.out.println("3---------" + dead.getDeathDate().toString());
//				boolean sameDay = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
//				        && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
				// if (!sameDay) {
				// System.out.println("modificare");
				// errors.rejectValue("funeralDate", "IsNotcurrentDate", new
				// Object[] { "Funeral_Date" },
				// "Campul 'Data inmormantarii' nu este data curenta.");
				//
				//
				// nu trebuie sa se faca programarea exact in ziua curenta.. se
				// poate face si peste 2-3 zile
				// trebuie verificata si data mortii
				// }
			}
		}
	}

}
