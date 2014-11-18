package ro.immortals.dao.impl;

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
		return entityManager.createQuery("SELECT d FROM Dead d", Dead.class)
				.getResultList();
	}

	@Override
	public Dead getById(Integer id) {
		return entityManager.find(Dead.class, id);
	}
}
