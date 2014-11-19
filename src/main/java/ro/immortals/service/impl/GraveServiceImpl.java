package ro.immortals.service.impl;

import java.util.List;

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
}
