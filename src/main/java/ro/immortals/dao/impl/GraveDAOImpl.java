package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.GraveDAO;
import ro.immortals.model.Grave;
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
		return entityManager.createQuery("SELECT g FROM graves g", Grave.class)
				.getResultList();
	}

	@Override
	public Grave getById(Integer id) {
		return entityManager.find(Grave.class, id);
	}
	
	@Override
	public Grave getByNumberAndPlot(String number,Integer plotId) {
		List<Grave> graveList = entityManager.createQuery("from graves g where g.number= :number and g.plot_id=:id", Grave.class)
		        .setParameter("number", number).setParameter("plot_id", plotId).getResultList();
		if (graveList.size() > 0)
			return graveList.get(0);
		else
			return null;
	}
}
