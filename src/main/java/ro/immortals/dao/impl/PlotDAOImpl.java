package ro.immortals.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ro.immortals.dao.PlotDAO;
import ro.immortals.model.Plot;

@Repository
public class PlotDAOImpl implements PlotDAO{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Plot plot) {
		entityManager.persist(plot);

	}

	@Override
	public void update(Plot plot) {
		entityManager.merge(plot);

	}

	@Override
	public void delete(Plot plot) {
		entityManager.remove(plot);

	}

	@Override
	public List<Plot> getAll() {
		return entityManager.createQuery("SELECT p FROM Plot p", Plot.class).getResultList();
	}

	@Override
	public Plot getById(Integer id) {
		return entityManager.find(Plot.class, id);
	}
	
	@Override
	public Plot getByNameAndCemetery(String name,Integer cId) {
		List<Plot> plotList = entityManager.createQuery("from Plot p where p.name= :name and p.cemetery.id=:cId", Plot.class)
		        .setParameter("name", name).setParameter("cId", cId).getResultList();
		if (plotList.size() > 0)
			return plotList.get(0);
		else
			return null;
	}
}
