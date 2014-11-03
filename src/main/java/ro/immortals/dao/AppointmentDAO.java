package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.Appointment;

public interface AppointmentDAO {

	public void add(Appointment appointment);

	public void update(Appointment appointment);

	public void delete(Appointment appointment);

	public List<Appointment> getAll();

	public Appointment getByCode(String code);

}
