package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.UserDAO;
import ro.immortals.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(User user) {
		entityManager.persist(user);

	}

	@Override
	public void update(User user) {
		entityManager.merge(user);

	}

	@Override
	public void delete(User user) {
		entityManager.remove(user);

	}

	@Override
	public List<User> getAll() {
		return entityManager.createQuery("SELECT u FROM users u", User.class)
				.getResultList();
	}

	@Override
	public User getByUsername(String username) {
		return entityManager.find(User.class, username);
	}
}
