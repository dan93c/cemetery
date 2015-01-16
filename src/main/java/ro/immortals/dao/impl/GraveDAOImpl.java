package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.GraveDAO;
import ro.immortals.model.Grave;

@Repository
public class GraveDAOImpl implements GraveDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Grave grave) {
		entityManager.persist(grave);

	}

	@Override
	public void update(Grave grave) {
		entityManager.merge(grave);

	}

	@Override
	public void delete(Grave grave) {
		entityManager.remove(grave);

	}

	@Override
	public List<Grave> getAll() {
		return entityManager.createQuery("SELECT g FROM Grave g", Grave.class).getResultList();
	}

	@Override
	public Grave getById(Integer id) {
		return entityManager.find(Grave.class, id);
	}

	@Override
	public Grave getByNumberAndPlot(String number, Integer plotId) {
		List<Grave> graveList = entityManager
		        .createQuery("from Grave g where g.nrGrave= :nrGrave and g.plot.id=:plotId", Grave.class)
		        .setParameter("nrGrave", number).setParameter("plotId", plotId).getResultList();
		if (graveList.size() > 0)
			return graveList.get(0);
		else
			return null;
	}

	@Override
	public List<Grave> getAllByPage(Integer offset, Integer nrOfRecords) {
		return entityManager
		        .createQuery("SELECT g FROM Grave g order by g.plot.cemetery.name,g.plot.name,g.nrGrave", Grave.class)
		        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();

	}

	@Override
	public List<Grave> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords) {
		if (search == null || search.isEmpty()) {
			if (order.equals("1")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM Grave g left join  g.concessionContracts c"
				                        + " order by c.firstName,c.lastName,c.emailAddress, g.plot.name", Grave.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM Grave g left join  g.concessionContracts c"
				                        + " order by c.address,g.plot.name", Grave.class).setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("3")) {
				return entityManager
				        .createQuery("SELECT g FROM Grave g " + " order by g.observations,g.nrGrave", Grave.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else {
				return entityManager
				        .createQuery("SELECT g FROM Grave g " + " order by g.plot.cemetery.name,g.plot.name,g.nrGrave",
				                Grave.class).setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			}
		} else {
			if (order.equals("1")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM Grave g left join  g.concessionContracts c where "
				                        + " g.nrGrave like :search" + " or g.surface like :search"
				                        + " or g.observations like :search" + " or g.plot.name like :search"
				                        + " or g.plot.cemetery.name like :search"
				                        + " order by c.firstName,c.lastName,c.emailAddress,g.plot.name", Grave.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM Grave g left join  g.concessionContracts c where "
				                        + " g.nrGrave like :search" + " or g.surface like :search"
				                        + " or g.observations like :search" + " or g.plot.name like :search"
				                        + " or g.plot.cemetery.name like :search" + " order by c.address,g.plot.name",
				                Grave.class).setParameter("search", "%" + search + "%").setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("3")) {
				return entityManager
				        .createQuery(
				                "SELECT g FROM Grave g where " + " g.nrGrave like :search"
				                        + " or g.surface like :search" + " or g.observations like :search"
				                        + " or g.plot.name like :search" + " or g.plot.cemetery.name like :search"
				                        + " order by g.observations,g.nrGrave", Grave.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			} else {
				return entityManager
				        .createQuery(
				                "SELECT g FROM Grave g where " + " g.nrGrave like :search"
				                        + " or g.surface like :search" + " or g.observations like :search"
				                        + " or g.plot.name like :search" + " or g.plot.cemetery.name like :search"
				                        + " order by g.plot.cemetery.name,g.plot.name,g.nrGrave", Grave.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			}
		}
	}

	@Override
	public Integer getAllSearchBySize(String search) {
		if (search == null || search.isEmpty()) {
			return entityManager.createQuery("SELECT g FROM Grave g", Grave.class).getResultList().size();
		} else {
			return entityManager
			        .createQuery(
			                "SELECT g FROM Grave g where " + " g.nrGrave like :search" + " or g.surface like :search"
			                        + " or g.observations like :search" + " or g.plot.name like :search"
			                        + " or g.plot.cemetery.name like :search", Grave.class)
			        .setParameter("search", "%" + search + "%").getResultList().size();
		}
	}

	@Override
	public List<Grave> getAllMonumentsByPageOrderBySearch(String order, String search, Integer offset,
	        Integer nrOfRecords) {
		if (search == null || search.isEmpty()) {
			if (order.equals("1")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM Grave g left join  g.concessionContracts c "
				                + "where g.type!=''"
				                        + " order by c.firstName,c.lastName,c.emailAddress, g.plot.name", Grave.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM Grave g left join  g.concessionContracts c where g.type!=''"
				                        + " order by c.address,g.plot.name", Grave.class).setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("3")) {
				return entityManager
				        .createQuery("SELECT g FROM Grave g where g.type!=''" + " order by g.observations,g.nrGrave",
				                Grave.class).setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			} else {
				return entityManager
				        .createQuery(
				                "SELECT g FROM Grave g where g.type!=''"
				                        + " order by g.plot.cemetery.name,g.plot.name,g.nrGrave", Grave.class)
				        .setFirstResult(offset).setMaxResults(nrOfRecords).getResultList();
			}
		} else {
			if (order.equals("1")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM Grave g left join  g.concessionContracts c "
				                + "where g.type!='' and ( "
				                        + " g.nrGrave like :search" + " or g.surface like :search"
				                        + " or g.observations like :search" + " or g.plot.name like :search"
				                        + " or g.plot.cemetery.name like :search)"
				                        + " order by c.firstName,c.lastName,c.emailAddress,g.plot.name ", Grave.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			} else if (order.equals("2")) {
				return entityManager
				        .createQuery(
				                "SELECT distinct g FROM Grave g left join  g.concessionContracts c where g.type!='' and ( "
				                        + " g.nrGrave like :search" + " or g.surface like :search"
				                        + " or g.observations like :search" + " or g.plot.name like :search"
				                        + " or g.plot.cemetery.name like :search)" + " order by c.address,g.plot.name ",
				                Grave.class).setParameter("search", "%" + search + "%").setFirstResult(offset)
				        .setMaxResults(nrOfRecords).getResultList();
			} else if (order.equals("3")) {
				return entityManager
				        .createQuery(
				                "SELECT g FROM Grave g where g.type!='' and ( " + " g.nrGrave like :search"
				                        + " or g.surface like :search" + " or g.observations like :search"
				                        + " or g.plot.name like :search" + " or g.plot.cemetery.name like :search)"
				                        + " order by g.observations,g.nrGrave ", Grave.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			} else {
				return entityManager
				        .createQuery(
				                "SELECT g FROM Grave g where g.type!='' and ( " + " g.nrGrave like :search"
				                        + " or g.surface like :search" + " or g.observations like :search"
				                        + " or g.plot.name like :search" + " or g.plot.cemetery.name like :search)"
				                        + " order by g.plot.cemetery.name,g.plot.name,g.nrGrave ", Grave.class)
				        .setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(nrOfRecords)
				        .getResultList();
			}
		}
	}

	@Override
	public Integer getAllMonumentsSearchBySize(String search) {
		if (search == null || search.isEmpty()) {
			return entityManager.createQuery("SELECT g FROM Grave g where g.type!=''", Grave.class).getResultList()
			        .size();
		} else {
			return entityManager
			        .createQuery(
			                "SELECT g FROM Grave g where g.type!='' and ( " + " g.nrGrave like :search"
			                        + " or g.surface like :search" + " or g.observations like :search"
			                        + " or g.plot.name like :search" + " or g.plot.cemetery.name like :search )",
			                Grave.class).setParameter("search", "%" + search + "%").getResultList().size();
		}
	}

}
