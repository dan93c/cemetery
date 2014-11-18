package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.ConcessionContractDAO;
import ro.immortals.model.ConcessionContract;

@Repository
public class ConcessionContractDAOImpl implements ConcessionContractDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(ConcessionContract concessionContract) {
		entityManager.persist(concessionContract);

	}

	@Override
	public void update(ConcessionContract concessionContract) {
		entityManager.merge(concessionContract);

	}

	@Override
	public void delete(ConcessionContract concessionContract) {
		entityManager.remove(concessionContract);

	}

	@Override
	public List<ConcessionContract> getAll() {
		return entityManager.createQuery("SELECT c FROM ConcessionContract c",
				ConcessionContract.class).getResultList();
	}

	@Override
	public ConcessionContract getById(Integer id) {
		return entityManager.find(ConcessionContract.class, id);
	}
	
	@Override
	public ConcessionContract getByCnp(String cnp) {
		List<ConcessionContract> concessionContractList = entityManager.createQuery("from ConcessionContract c where c.cnp= :cnp", ConcessionContract.class)
		        .setParameter("cnp"	, cnp).getResultList();
		if (concessionContractList.size() > 0)
			return concessionContractList.get(0);
		else
			return null;
	}
}
