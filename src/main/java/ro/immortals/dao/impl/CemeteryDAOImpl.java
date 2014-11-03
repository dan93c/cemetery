package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.CemeteryDAO;
import ro.immortals.model.Cemetery;

@Repository
public class CemeteryDAOImpl implements CemeteryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Cemetery cemetery) {
		entityManager.persist(cemetery);

	}

	@Override
	public void update(Cemetery cemetery) {
		entityManager.merge(cemetery);

	}

	@Override
	public void delete(Cemetery cemetery) {
		entityManager.remove(cemetery);

	}

	@Override
	public List<Cemetery> getAll() {
		return entityManager.createQuery("SELECT c FROM Cemetery c", Cemetery.class).getResultList();
	}

	@Override
	public Cemetery getByCode(String code) {
		return entityManager.find(Cemetery.class, code);
	}

}
