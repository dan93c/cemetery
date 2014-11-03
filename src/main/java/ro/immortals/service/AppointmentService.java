package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Appointment;

public interface AppointmentService {

	public int add(Appointment appointment);

	public void update(Appointment appointment);

	public void delete(String code);

	public List<Appointment> getAll();

	public Appointment getByCode(String code);

	public boolean checkDuplicate(Appointment appointment);
}
