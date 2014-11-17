package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.ConcessionContractDAO;
import ro.immortals.model.ConcessionContract;
import ro.immortals.service.ConcessionContractService;

@Service
public class ConcessionContractServiceImpl implements ConcessionContractService {

	@Autowired
	private ConcessionContractDAO concessionContractDAO;

	@Override
	@Transactional
	public int add(ConcessionContract concessionContract) {
		if (checkDuplicate(concessionContract)) {
			concessionContractDAO.add(concessionContract);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(ConcessionContract concessionContract) {
		concessionContractDAO.update(concessionContract);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		ConcessionContract concessionContract = concessionContractDAO.getById(id);
		if (concessionContract != null) {
			concessionContractDAO.delete(concessionContract);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ConcessionContract> getAll() {
		return concessionContractDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ConcessionContract getById(Integer id) {
		return concessionContractDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(ConcessionContract concessionContract) {
		ConcessionContract existingConcessionContract = concessionContractDAO.getByCnp(concessionContract.getCnp());
		if (existingConcessionContract != null && (existingConcessionContract.getId() != concessionContract.getId())) {
			return false;
		}
		return true;
	}

}
