package ro.immortals.dao.impl;

import java.util.Calendar;
import java.util.Date;
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
		List<ConcessionContract> concessionContractList = entityManager
				.createQuery("from ConcessionContract c where c.cnp= :cnp",
						ConcessionContract.class).setParameter("cnp", cnp)
				.getResultList();
		if (concessionContractList.size() > 0)
			return concessionContractList.get(0);
		else
			return null;
	}

	@Override
	public Integer getAllSearchBySize(String search) {
		if (search == null || search.isEmpty()) {
			return entityManager
					.createQuery("select c FROM ConcessionContract c",
							ConcessionContract.class).getResultList().size();
		}
		return entityManager
				.createQuery(
						"FROM ConcessionContract c where c.cnp like :search or  c.firstName like :search or"
								+ "  c.lastName like :search or  c.receiptNr like :search or  c.address like :search",
						ConcessionContract.class)
				.setParameter("search", "%" + search + "%").getResultList()
				.size();
	}

	@Override
	public List<ConcessionContract> getAllByPageOrderBySearch(String order,
			String search, Integer offset, Integer nrOfRecords) {
		if (search == null || search.isEmpty()) {
			return entityManager
					.createQuery("select c FROM ConcessionContract c",
							ConcessionContract.class).setFirstResult(offset)
					.setMaxResults(nrOfRecords).getResultList();
		}
		return entityManager
				.createQuery(
						"FROM ConcessionContract c where c.cnp like :search or  c.firstName like :search or"
								+ "  c.lastName like :search or  c.receiptNr like :search or  c.address like :search",
						ConcessionContract.class)
				.setParameter("search", "%" + search + "%")
				.setFirstResult(offset).setMaxResults(nrOfRecords)
				.getResultList();
	}

	@Override
	public List<ConcessionContract> getAllGravesExpiredOnYears(String order,
			String search, Integer offset, Integer nrOfRecords) {
		if (search == null || search.isEmpty() || !search.matches("\bd{4}\b")) {
			Date date = Calendar.getInstance().getTime();

			return entityManager
					.createQuery(
							"select c FROM ConcessionContract c where c.expiredDate< :date order by c.expiredDate",
							ConcessionContract.class)
					.setParameter("date", date).setFirstResult(offset)
					.setMaxResults(nrOfRecords).getResultList();

		} else {
			Calendar start = Calendar.getInstance();
			start.set(Calendar.YEAR, Integer.parseInt(search));
			start.set(Calendar.MONTH, 1);
			start.set(Calendar.DAY_OF_MONTH, 1);

			Calendar end = Calendar.getInstance();
			end.set(Calendar.YEAR, Integer.parseInt(search));
			end.set(Calendar.MONTH, 12);
			end.set(Calendar.DAY_OF_MONTH, 31);

			return entityManager
					.createQuery(
							"FROM ConcessionContract c where c.expiredDate>= :start and "
									+ "c.expiredDate<= :end order by c.expiredDate",
							ConcessionContract.class).setParameter("end", end)
					.setParameter("start", start).setFirstResult(offset)
					.setMaxResults(nrOfRecords).getResultList();
		}
	}

	@Override
	public Integer getAllGravesExpiredOnYearsSize(String search) {

		if (search == null || search.isEmpty() || !search.matches("\bd{4}\b")) {
			Date date = Calendar.getInstance().getTime();
			return entityManager
					.createQuery(
							"select c FROM ConcessionContract c where c.expiredDate< :date",
							ConcessionContract.class)
					.setParameter("date", date).getResultList().size();
		} else {
			Calendar start = Calendar.getInstance();
			start.set(Calendar.YEAR, Integer.parseInt(search));
			start.set(Calendar.MONTH, 1);
			start.set(Calendar.DAY_OF_MONTH, 1);

			Calendar end = Calendar.getInstance();
			end.set(Calendar.YEAR, Integer.parseInt(search));
			end.set(Calendar.MONTH, 12);
			end.set(Calendar.DAY_OF_MONTH, 31);

			return entityManager
					.createQuery(
							"FROM ConcessionContract c where c.expiredDate>= :start and c.expiredDate<= :end",
							ConcessionContract.class).setParameter("end", end)
					.setParameter("start", start).getResultList().size();
		}
	}

}
