package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.ClaimBookDAO;
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

	@Override
	public Integer getAllSearchBySize(String search) {
		if (search == null || search.isEmpty()) {
			return entityManager
					.createQuery("select c FROM ClaimBook c", ClaimBook.class)
					.getResultList().size();
		}
		return entityManager
				.createQuery(
						"FROM ClaimBook c where c.claims like :search or  c.complainer like :search",
						ClaimBook.class)
				.setParameter("search", "%" + search + "%").getResultList()
				.size();
	}

	@Override
	public List<ClaimBook> getAllByPageOrderBySearch(String order,
			String search, Integer offset, Integer nrOfRecords) {
		if (search == null || search.isEmpty()) {
			if (order.equals("1")) {
				return entityManager
						.createQuery(
								"select c FROM ClaimBook order by c.complainer",
								ClaimBook.class).setFirstResult(offset)
						.setMaxResults(nrOfRecords).getResultList();
			} else {
				return entityManager
						.createQuery("select c FROM ClaimBook c",
								ClaimBook.class).setFirstResult(offset)
						.setMaxResults(nrOfRecords).getResultList();
			}
		}
		if (order.equals("1")) {
			return entityManager
					.createQuery(
							"FROM ClaimBook c where c.claims like :search or  c.complainer like :search order by c.complainer",
							ClaimBook.class)
					.setParameter("search", "%" + search + "%")
					.setFirstResult(offset).setMaxResults(nrOfRecords)
					.getResultList();
		} else {
			return entityManager
					.createQuery(
							"FROM ClaimBook c where c.claims like :search or  c.complainer like :search",
							ClaimBook.class)
					.setParameter("search", "%" + search + "%")
					.setFirstResult(offset).setMaxResults(nrOfRecords)
					.getResultList();
		}
	}

}
