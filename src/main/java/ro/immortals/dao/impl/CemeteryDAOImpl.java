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
		return entityManager.createQuery("FROM Cemetery c", Cemetery.class).getResultList();
	}

	@Override
	public Cemetery getById(Integer id) {
		return entityManager.find(Cemetery.class, id);
	}

	@Override
	public Cemetery getByName(String name) {
		List<Cemetery> cemeteryList = entityManager.createQuery("from Cemetery c where c.name= :name", Cemetery.class)
		        .setParameter("name", name).getResultList();
		if (cemeteryList.size() > 0)
			return cemeteryList.get(0);
		else
			return null;
	}

}
