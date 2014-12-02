package ro.immortals.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.DeadDAO;
import ro.immortals.model.Dead;

@Repository
public class DeadDAOImpl implements DeadDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Dead dead) {
		entityManager.persist(dead);

	}

	@Override
	public void update(Dead dead) {
		entityManager.merge(dead);

	}

	@Override
	public void delete(Dead dead) {
		entityManager.remove(dead);

	}

	@Override
	public List<Dead> getAll() {
		return entityManager.createQuery("SELECT d FROM Dead d", Dead.class).getResultList();
	}

	@Override
	public Dead getById(Integer id) {
		return entityManager.find(Dead.class, id);
	}

	@Override
	public Dead getByGraveAndFuneralDate(String nrGrave, Date funeralDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date;
		Calendar c = Calendar.getInstance();
		c.setTime(funeralDate);
		date = format.format(c.getTime());
		System.out.println(funeralDate + "    " + date);
		List<Dead> funeralList = entityManager
		        .createQuery("from Dead d where d.grave.nrGrave= :nrGrave and d.funeralDate= '" + date + "'",
		                Dead.class).setParameter("nrGrave", nrGrave).getResultList();
		if (funeralList.size() > 0)
			return funeralList.get(0);
		else
			return null;

	}
}
