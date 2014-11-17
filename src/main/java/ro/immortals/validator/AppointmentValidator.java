package ro.immortals.validator;

import java.util.Calendar;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ro.immortals.model.Appointment;

public class AppointmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Appointment.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "funeralDate",
				"error.appointment.funeralDate.required");

		Appointment appointment = (Appointment) target;

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(new Date());
		calendar2.setTime(appointment.getFuneralDate());
		boolean sameDay = calendar1.get(Calendar.YEAR) == calendar2
				.get(Calendar.YEAR)
				&& calendar1.get(Calendar.DAY_OF_YEAR) == calendar2
						.get(Calendar.DAY_OF_YEAR);
		if (!sameDay) {
			System.out.println("modificare");
			errors.rejectValue("funeralDate", "IsNotcurrentDate",
					new Object[] { "Funeral_Date" },
					"Campul 'Funeral_Date' nu este data curenta.");
		}

	}

}
