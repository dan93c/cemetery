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

}
