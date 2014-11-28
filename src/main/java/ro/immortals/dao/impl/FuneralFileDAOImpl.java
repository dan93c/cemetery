package ro.immortals.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ro.immortals.dao.FuneralFileDAO;
import ro.immortals.model.FuneralFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class FuneralFileDAOImpl implements FuneralFileDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(FuneralFile funeralFile) {
		entityManager.persist(funeralFile);

	}

	@Override
	public void update(FuneralFile funeralFile) {
		entityManager.merge(funeralFile);

	}

	@Override
	public void delete(FuneralFile funeralFile) {
		entityManager.remove(funeralFile);

	}

	@Override
	public List<FuneralFile> getAll() {
		return entityManager.createQuery("SELECT f FROM FuneralFile f", FuneralFile.class).getResultList();
	}

	@Override
	public FuneralFile getById(Integer id) {
		return entityManager.find(FuneralFile.class, id);
	}

	@Override
	public FuneralFile getByGraveAndFuneralDate(String nrGrave, Date funeralDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date;
		Calendar c = Calendar.getInstance();
		c.setTime(funeralDate);
		date = format.format(c.getTime());
		System.out.println(funeralDate+"    "+date);
		List<FuneralFile> funeralList = entityManager
		        .createQuery("from FuneralFile f where f.grave.nrGrave= :nrGrave and f.funeralDate= '"+date+"'",
		                FuneralFile.class).setParameter("nrGrave", nrGrave)
		        .getResultList();
		if (funeralList.size() > 0)
			return funeralList.get(0);
		else
			return null;

	}

}
