package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.AppointmentDAO;
import ro.immortals.model.Appointment;
import ro.immortals.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Override
	@Transactional
	public int add(Appointment appointment) {
		if (checkDuplicate(appointment)) {
			appointmentDAO.add(appointment);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(Appointment appointment) {
		appointmentDAO.update(appointment);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Appointment appointment = appointmentDAO.getById(id);
		if (appointment != null) {
			appointmentDAO.delete(appointment);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appointment> getAll() {
		return appointmentDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Appointment getById(Integer id) {
		return appointmentDAO.getById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(Appointment appointment) {
		Appointment existingAppointment = appointmentDAO.getByDeadId(appointment.getDead().getId());
		if (existingAppointment != null && (existingAppointment.getId() != appointment.getId())) {
			return false;
		}
		return true;
	}

}
