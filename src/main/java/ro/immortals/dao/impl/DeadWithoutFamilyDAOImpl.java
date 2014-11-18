package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.DeadWithoutFamilyDAO;
import ro.immortals.model.DeadWithoutFamily;

@Repository
public class DeadWithoutFamilyDAOImpl implements DeadWithoutFamilyDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(DeadWithoutFamily deadWithoutFamily) {
		entityManager.persist(deadWithoutFamily);

	}

	@Override
	public void update(DeadWithoutFamily deadWithoutFamily) {
		entityManager.merge(deadWithoutFamily);

	}

	@Override
	public void delete(DeadWithoutFamily deadWithoutFamily) {
		entityManager.remove(deadWithoutFamily);

	}

	@Override
	public List<DeadWithoutFamily> getAll() {
		return entityManager.createQuery("SELECT d FROM DeadWithoutFamily d", DeadWithoutFamily.class).getResultList();
	}

	@Override
	public DeadWithoutFamily getById(Integer id) {
		return entityManager.find(DeadWithoutFamily.class, id);
	}
}
