package ro.immortals.dao.impl;

import java.util.List;

import ro.immortals.dao.AppointmentDAO;
import ro.immortals.model.Appointment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Appointment appointment) {
		entityManager.persist(appointment);

	}

	@Override
	public void update(Appointment appointment) {
		entityManager.merge(appointment);

	}

	@Override
	public void delete(Appointment appointment) {
		entityManager.remove(appointment);

	}

	@Override
	public List<Appointment> getAll() {
		return entityManager.createQuery("SELECT a FROM Appointments a",
				Appointment.class).getResultList();
	}

	@Override
	public Appointment getByCode(String code) {
		return entityManager.find(Appointment.class, code);
	}

}
