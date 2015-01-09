package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.ClaimBookDAO;
import ro.immortals.model.ClaimBook;
import ro.immortals.service.ClaimBookService;

@Service
public class ClaimBookServiceImpl implements ClaimBookService {

	@Autowired
	private ClaimBookDAO claimBookDAO;

	@Override
	@Transactional
	public int add(ClaimBook claimBook) {
		claimBookDAO.add(claimBook);
		return 1;
	}

	@Override
	@Transactional
	public void update(ClaimBook claimBook) {
		claimBookDAO.update(claimBook);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		ClaimBook claimBook = claimBookDAO.getById(id);
		if (claimBook != null) {
			claimBookDAO.delete(claimBook);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClaimBook> getAll() {
		return claimBookDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ClaimBook getById(Integer id) {
		return claimBookDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSearchBySize(String search) {
		return claimBookDAO.getAllSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClaimBook> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords) {
		return claimBookDAO.getAllByPageOrderBySearch(order, search, offset, nrOfRecords);
	}

}
