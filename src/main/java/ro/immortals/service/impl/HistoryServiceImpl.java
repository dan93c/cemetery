package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.HistoryDAO;
import ro.immortals.model.History;
import ro.immortals.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryDAO historyDAO;

	@Override
	@Transactional
	public int add(History history) {
		historyDAO.add(history);
		return 1;
	}

	@Override
	@Transactional
	public void update(History history) {
		historyDAO.update(history);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		History history = historyDAO.getById(id);
		if (history != null) {
			historyDAO.delete(history);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<History> getAll() {
		return historyDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public History getById(Integer id) {
		return historyDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSizeSearchBy(String search) {
		if (search != null) {
			return historyDAO.getAllSizeSearchBy(search);
		}
		return historyDAO.getAll().size();
	}

	@Override
	@Transactional(readOnly = true)
	public List<History> getByPageSearch(String search, Integer offset,
			Integer nrOfRecords) {
		if (search != null) {
			return historyDAO.getByPageSearch(search, offset, nrOfRecords);
		}
		return historyDAO.getAllByPage(offset, nrOfRecords);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSizeFilterBySearch(String criteria, String search) {
		if (search == null) {
			search = "";
		}
		if (criteria == null || criteria.contentEquals("0")) {
			return historyDAO.getAllSizeSearchBy(search);
		}
		return historyDAO.getAllSizeFilterBySearch(criteria, search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<History> getByPageFilterBySearch(String criteria,
			String search, Integer offset, Integer nrOfRecords) {
		if (search == null) {
			search = "";
		}
		if (criteria == null || criteria.contentEquals("0")) {
			return historyDAO.getByPageSearch(search, offset, nrOfRecords);
		}
		return historyDAO.getByPageFilterBySearch(criteria, search, offset,
				nrOfRecords);
	}

}
