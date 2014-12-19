package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.HistoryDAO;
import ro.immortals.model.History;

@Repository
public class HistoryDAOImpl implements HistoryDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(History history) {
		entityManager.persist(history);

	}

	@Override
	public void update(History history) {
		entityManager.merge(history);

	}

	@Override
	public void delete(History history) {
		entityManager.remove(history);

	}

	@Override
	public List<History> getAll() {
		return entityManager.createQuery("SELECT h FROM History h order by h.modificationDate desc", History.class)
		        .getResultList();
	}

	@Override
	public History getById(Integer id) {
		return entityManager.find(History.class, id);
	}

	@Override
	public List<History> getAllByPage(Integer offset, Integer nrOfRecords) {
		return entityManager.createQuery("SELECT h FROM History h order by h.modificationDate desc", History.class)
		        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
	}

	@Override
	public List<History> getByPageSearch(String search, Integer offset, Integer nrOfRecords) {
		return entityManager
		        .createQuery(
		                "SELECT h FROM History h where h.modifiedObject like :search or h.user.username like :search or "
		                        + "h.details like :search order by h.modificationDate desc", History.class)
		        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
		        .getResultList();
	}

	@Override
	public Integer getAllSizeFilterBySearch(String criteria, String search) {
		return entityManager
		        .createQuery(
		                "SELECT h FROM History h where h.actionName =:criteria and ( h.modifiedObject like :search or h.user.username like :search "
		                        + "or h.details like :search)", History.class).setParameter("criteria", criteria)
		        .setParameter("search", "%" + search + "%").getResultList().size();
	}

	@Override
	public Integer getAllSizeSearchBy(String search) {
		return entityManager
		        .createQuery(
		                "SELECT h FROM History h where h.modifiedObject like :search or h.user.username like :search "
		                        + "or h.details like :search", History.class)
		        .setParameter("search", "%" + search + "%").getResultList().size();
	}

	@Override
	public List<History> getByPageFilterBySearch(String criteria, String search, Integer offset, Integer nrOfRecords) {
		return entityManager
		        .createQuery(
		                "SELECT h FROM History h where h.actionName =:criteria and "
		                        + "( h.modifiedObject like :search or h.user.username like :search "
		                        + "or h.details like :search)", History.class).setParameter("criteria", criteria)
		        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
	}
	
}
