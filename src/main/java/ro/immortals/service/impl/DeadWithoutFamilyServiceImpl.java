package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.DeadWithoutFamilyDAO;
import ro.immortals.model.DeadWithoutFamily;
import ro.immortals.service.DeadWithoutFamilyService;

@Service
public class DeadWithoutFamilyServiceImpl implements DeadWithoutFamilyService {

	@Autowired
	private DeadWithoutFamilyDAO deadWithoutFamilyDAO;

	@Override
	@Transactional
	public int add(DeadWithoutFamily deadWithoutFamily) {
		deadWithoutFamilyDAO.add(deadWithoutFamily);
		return 1;
	}

	@Override
	@Transactional
	public void update(DeadWithoutFamily deadWithoutFamily) {
		deadWithoutFamilyDAO.update(deadWithoutFamily);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		DeadWithoutFamily deadWithoutFamily = deadWithoutFamilyDAO.getById(id);
		if (deadWithoutFamily != null) {
			deadWithoutFamilyDAO.delete(deadWithoutFamily);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DeadWithoutFamily> getAll() {
		return deadWithoutFamilyDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DeadWithoutFamily getById(Integer id) {
		return deadWithoutFamilyDAO.getById(id);
	}

}
