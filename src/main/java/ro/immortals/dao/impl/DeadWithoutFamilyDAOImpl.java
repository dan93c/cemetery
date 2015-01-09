package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.DeadWithoutFamilyDAO;
import ro.immortals.model.DeadWithoutFamily;

@Repository
public class DeadWithoutFamilyDAOImpl implements DeadWithoutFamilyDAO {

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

	@Override
	public Integer getAllSearchBySize(String search) {
		if (search == null || search.isEmpty()) {
			return entityManager.createQuery("select d FROM DeadWithoutFamily d", DeadWithoutFamily.class)
			        .getResultList().size();
		}
		return entityManager
		        .createQuery(
		                "FROM DeadWithoutFamily d where d.funeralCertificate like :search or  d.imlRequest like :search",
		                DeadWithoutFamily.class).setParameter("search", "%" + search + "%").getResultList().size();
	}

	@Override
	public List<DeadWithoutFamily> getAllByPageOrderBySearch(String order, String search, Integer offset,
	        Integer nrOfRecords) {
		if (search == null || search.isEmpty()) {
			if (order.equals("1")) {
				return entityManager
				        .createQuery("select d FROM DeadWithoutFamily d order by d.funeralCertificate",
				                DeadWithoutFamily.class).setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery("select d FROM DeadWithoutFamily d order by d.imlRequest", DeadWithoutFamily.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else {
				return entityManager.createQuery("select d FROM DeadWithoutFamily d", DeadWithoutFamily.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			}
		} else {
			if (order.equals("1")) {
				return entityManager
				        .createQuery(
				                "select d FROM DeadWithoutFamily d where d.funeralCertificate like :search or  d.imlRequest like :search"
				                        + "   order by d.funeralCertificate", DeadWithoutFamily.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery(
				                "select d FROM DeadWithoutFamily d where d.funeralCertificate like :search or  d.imlRequest like :search"
				                        + " order by d.imlRequest", DeadWithoutFamily.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			} else {
				return entityManager
				        .createQuery(
				                "select d FROM DeadWithoutFamily d where d.funeralCertificate like :search or  d.imlRequest like :search",
				                DeadWithoutFamily.class).setParameter("search", "%" + search + "%")
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			}
		}
	}
}
