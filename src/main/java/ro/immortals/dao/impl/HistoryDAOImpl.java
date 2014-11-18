package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.HistoryDAO;
import ro.immortals.model.History;

@Repository
public class HistoryDAOImpl implements HistoryDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(History history) {
		entityManager.persist(history);

	}

	@Override
	public void update(History history) {
		entityManager.merge(history);

	}

	@Override
	public void delete(History history) {
		entityManager.remove(history);

	}

	@Override
	public List<History> getAll() {
		return entityManager.createQuery("SELECT h FROM History h", History.class).getResultList();
	}

	@Override
	public History getById(Integer id) {
		return entityManager.find(History.class, id);
	}
}
