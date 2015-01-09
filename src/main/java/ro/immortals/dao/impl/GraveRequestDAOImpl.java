package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.GraveRequestDAO;
import ro.immortals.model.GraveRequest;

@Repository
public class GraveRequestDAOImpl implements GraveRequestDAO {
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
		return entityManager.createQuery("SELECT g FROM GraveRequest g", GraveRequest.class).getResultList();
	}

	@Override
	public GraveRequest getById(Integer id) {
		return entityManager.find(GraveRequest.class, id);
	}

	@Override
	public GraveRequest getByNrInfocet(String nr) {
		List<GraveRequest> graveRequestList = entityManager
		        .createQuery("from GraveRequest g where g.nrInfocet= :nr", GraveRequest.class).setParameter("nr", nr)
		        .getResultList();
		if (graveRequestList.size() > 0)
			return graveRequestList.get(0);
		else
			return null;
	}

	@Override
	public List<GraveRequest> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords) {
		if (search == null || search.isEmpty()) {
			if (order.equals("1")) {
				return entityManager
				        .createQuery("SELECT g FROM GraveRequest g order by g.registrationDate desc",
				                GraveRequest.class).setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery("SELECT g FROM GraveRequest g order by g.nrInfocet", GraveRequest.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();

			} else {
				return entityManager
				        .createQuery("SELECT g FROM GraveRequest g order by g.registrationDate,g.nrInfocet",
				                GraveRequest.class).setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			}
		} else {
			if (order.equals("1")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM GraveRequest g where " + " g.nrInfocet like :search"
				                        + " or g.solvingStage like :search" + " order by g.registrationDate desc",
				                GraveRequest.class).setParameter("search", "%" + search + "%").setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM GraveRequest g  where " + " g.nrInfocet like :search"
				                        + " or g.solvingStage like :search" + " order by g.nrInfocet",
				                GraveRequest.class).setParameter("search", "%" + search + "%").setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			} else {
				return entityManager
				        .createQuery(
				                "SELECT g FROM GraveRequest g where g.nrInfocet like :search"
				                        + " or g.solvingStage like :search"
				                        + " order by g.registrationDate,g.nrInfocet", GraveRequest.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			}
		}
	}

	@Override
	public Integer getAllSearchBySize(String search) {
		if (search == null || search.isEmpty()) {
			return entityManager.createQuery("SELECT g FROM GraveRequest g", GraveRequest.class).getResultList().size();
		} else {
			return entityManager
			        .createQuery(
			                "SELECT g FROM GraveRequest g where g.nrInfocet like :search"
			                        + " or g.solvingStage like :search", GraveRequest.class)
			        .setParameter("search", "%" + search + "%").getResultList().size();
		}
	}
}
