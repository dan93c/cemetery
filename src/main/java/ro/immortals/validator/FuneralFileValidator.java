package ro.immortals.validator;

import java.util.Calendar;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.FuneralFile;

public class FuneralFileValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return FuneralFile.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funeralDate", "error.field.required",
		        new Object[] { "Data inmormantarii" }, "Data inmormantarii este necesara");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dead.deathDate", "error.field.required",
		        new Object[] { "Data mortii" }, "Data mortii este necesara");

		FuneralFile funeralFile = (FuneralFile) target;
		System.out.println("1");
		if (funeralFile.getFuneralDate() != null) {
			if (funeralFile.getDead().getDeathDate() == null) {
				errors.rejectValue("dead.deathDate", "error.field.required", new Object[] { "Data mortii" },
				        "Data mortii este necesara");
			} else {
				System.out.println("2---------" + funeralFile.getFuneralDate().toString());
				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance();
				calendar1.setTime(new Date());
				calendar2.setTime(funeralFile.getFuneralDate());
				System.out.println("3---------" + funeralFile.getDead().getDeathDate().toString());
				boolean sameDay = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
				        && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
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
				// alte verificari: nume,prenume,religia,..pe scurt tot..la
				// toate trebuie sa nu depaseasca lungimea maxima.. iar la nume,
				// prenume sa nu fie empty/whitespace
				// }
			}
		}
	}

}
