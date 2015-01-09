package ro.immortals.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.GraveDAO;
import ro.immortals.model.Grave;
import ro.immortals.service.GraveService;

@Service
public class GraveServiceImpl implements GraveService {

	@Autowired
	private GraveDAO graveDAO;

	@Override
	@Transactional
	public int add(Grave grave) {
		if (checkDuplicate(grave)) {
			graveDAO.add(grave);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(Grave grave) {
		graveDAO.update(grave);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Grave grave = graveDAO.getById(id);
		if (grave != null) {
			graveDAO.delete(grave);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAll() {
		return graveDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Grave getById(Integer id) {
		return graveDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(Grave grave) {
		Grave existingGrave = graveDAO.getByNumberAndPlot(grave.getNrGrave(), grave.getPlot().getId());
		if (existingGrave != null && (existingGrave.getId() != grave.getId())) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkGraveExistence(Grave grave, Integer plotId, Integer cemeteryId) {
		List<Grave> graves = graveDAO.getAll();
		for (Grave g : graves) {
			if (g.getId() == grave.getId() && g.getPlot().getId() == plotId
			        && g.getPlot().getCemetery().getId() == cemeteryId) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllByPage(Integer offset, Integer nrOfRecords) {
		return graveDAO.getAllByPage(offset, nrOfRecords);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllByPageWithContractsAndDeads(Integer offset, Integer nrOfRecords) {
		List<Grave> graves = graveDAO.getAllByPage(offset, nrOfRecords);
		for (Grave g : graves) {
			Hibernate.initialize(g.getConcessionContracts());
			Hibernate.initialize(g.getDeads());
			Hibernate.initialize(g.getDeadsWithoutFamily());
		}
		return graves;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords) {
		List<Grave> graves = graveDAO.getAllByPageOrderBySearch(order, search, offset, nrOfRecords);
		for (Grave g : graves) {
			Hibernate.initialize(g.getConcessionContracts());
			Hibernate.initialize(g.getDeads());
			Hibernate.initialize(g.getDeadsWithoutFamily());
		}
		return graves;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSearchBySize(String search) {
		return graveDAO.getAllSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllMonumentsByPageOrderBySearch(String order, String search, Integer offset,
	        Integer nrOfRecords) {
		List<Grave> graves = graveDAO.getAllMonumentsByPageOrderBySearch(order, search, offset, nrOfRecords);
		for (Grave g : graves) {
			Hibernate.initialize(g.getConcessionContracts());
			Hibernate.initialize(g.getDeads());
			Hibernate.initialize(g.getDeadsWithoutFamily());
		}
		return graves;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllMonumentsSearchBySize(String search) {
		return graveDAO.getAllMonumentsSearchBySize(search);
	}
}
