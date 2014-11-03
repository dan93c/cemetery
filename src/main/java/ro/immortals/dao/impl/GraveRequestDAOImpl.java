package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.GraveRequestDAO;
import ro.immortals.model.GraveRequest;

@Repository
public class GraveRequestDAOImpl implements GraveRequestDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(GraveRequest graveRequest) {
		entityManager.persist(graveRequest);

	}

	@Override
	public void update(GraveRequest graveRequest) {
		entityManager.merge(graveRequest);

	}

	@Override
	public void delete(GraveRequest graveRequest) {
		entityManager.remove(graveRequest);

	}

	@Override
	public List<GraveRequest> getAll() {
		return entityManager.createQuery("SELECT g FROM grave_requests g",
				GraveRequest.class).getResultList();
	}

	@Override
	public GraveRequest getByCode(String code) {
		return entityManager.find(GraveRequest.class, code);
	}
}
