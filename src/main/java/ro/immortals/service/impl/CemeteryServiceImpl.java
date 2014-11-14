package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.CemeteryDAO;
import ro.immortals.model.Cemetery;
import ro.immortals.service.CemeteryService;

@Service
public class CemeteryServiceImpl implements CemeteryService {

	@Autowired
	private CemeteryDAO cemeteryDAO;

	@Override
	@Transactional
	public int add(Cemetery cemetery) {
		if (checkDuplicate(cemetery)) {
			cemeteryDAO.add(cemetery);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(Cemetery cemetery) {
		cemeteryDAO.update(cemetery);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Cemetery cemetery = cemeteryDAO.getById(id);
		if (cemetery != null) {
			cemeteryDAO.delete(cemetery);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cemetery> getAll() {
		return cemeteryDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cemetery getById(Integer id) {
		return cemeteryDAO.getById(id);
	}

	public Cemetery getByName(String name) {
		return cemeteryDAO.getByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(Cemetery cemetery) {
		Cemetery existingCemetery = cemeteryDAO.getByName(cemetery.getName());
		if (existingCemetery != null && (existingCemetery.getId() != cemetery.getId())) {
			return false;
		}
		return true;
	}

}
