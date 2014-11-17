package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.GraveRequestDAO;
import ro.immortals.model.GraveRequest;
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
	public GraveRequest getById(Integer id) {
		return entityManager.find(GraveRequest.class, id);
	}
	
	@Override
	public GraveRequest getByNrInfocet(String nr) {
		List<GraveRequest> graveRequestList = entityManager.createQuery("from grave_requests g where g.nr_infocet= :nr", GraveRequest.class)
		        .setParameter("nr", nr).getResultList();
		if (graveRequestList.size() > 0)
			return graveRequestList.get(0);
		else
			return null;
	}
}
