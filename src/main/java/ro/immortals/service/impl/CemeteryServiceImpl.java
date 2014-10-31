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
	public void delete(String code) {
		Cemetery cemetery = cemeteryDAO.getByCode(code);
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
	public Cemetery getByCode(String code) {
		return cemeteryDAO.getByCode(code);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(Cemetery cemetery) {
		Cemetery c = getByCode(cemetery.getCode());
		if (c == null) {
			return true;
		}
		return false;
	}

}
