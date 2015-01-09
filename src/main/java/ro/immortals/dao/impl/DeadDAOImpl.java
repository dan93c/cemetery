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

	@Override
	public Integer getAllSearchBySize(String search) {
		if (search == null || search.isEmpty()) {
			return entityManager.createQuery("select d FROM Dead d", Dead.class).getResultList().size();
		}
		return entityManager
		        .createQuery(
		                "FROM Dead d where d.firstName like :search or  d.lastName like :search or  d.religion like :search",
		                Dead.class).setParameter("search", "%" + search + "%").getResultList().size();
	}

	@Override
	public List<Dead> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords) {
		if (search == null || search.isEmpty()) {
			if (order.equals("1")) {
				return entityManager
				        .createQuery("select d FROM Dead d order by d.funeralDate,d.firstName,d.lastName", Dead.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery("select d FROM Dead d order by d.funeralDate desc,d.firstName,d.lastName",
				                Dead.class).setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("3")) {
				return entityManager.createQuery("select d FROM Dead d order by d.firstName,d.lastName,d.funeralDate", Dead.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else {
				return entityManager.createQuery("select d FROM Dead d", Dead.class).setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			}
		} else {
			if (order.equals("1")) {
				return entityManager
				        .createQuery(
				                "select d FROM Dead d where d.firstName like :search or  d.lastName like :search or"
				                        + "  d.religion like :search order by d.funeralDate,d.firstName,d.lastName",
				                Dead.class).setParameter("search", "%" + search + "%").setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery(
				                "select d FROM Dead d where d.firstName like :search or  d.lastName like :search or"
				                        + "  d.religion like :search order by d.funeralDate desc,d.firstName,d.lastName",
				                Dead.class).setParameter("search", "%" + search + "%").setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("3")) {
				return entityManager
				        .createQuery(
				                "select d FROM Dead d where d.firstName like :search or  d.lastName like :search or"
				                        + "  d.religion like :search order by d.firstName,d.lastName,d.funeralDate", Dead.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			} else {
				return entityManager
				        .createQuery(
				                "select d FROM Dead d where d.firstName like :search or  d.lastName like :search or"
				                        + " d.religion like :search", Dead.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			}
		}
	}
}
