package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.ClaimBookDAO;
import ro.immortals.model.ClaimBook;
import ro.immortals.model.ClaimBook;

@Repository
public class ClaimBookDAOImpl implements ClaimBookDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(ClaimBook claimBook) {
		entityManager.persist(claimBook);

	}

	@Override
	public void update(ClaimBook claimBook) {
		entityManager.merge(claimBook);

	}

	@Override
	public void delete(ClaimBook claimBook) {
		entityManager.remove(claimBook);

	}

	@Override
	public List<ClaimBook> getAll() {
		return entityManager.createQuery("SELECT c FROM ClaimBook c",
				ClaimBook.class).getResultList();
	}

	@Override
	public ClaimBook getById(Integer id) {
		return entityManager.find(ClaimBook.class, id);
	}
}
