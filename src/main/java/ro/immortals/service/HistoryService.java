package ro.immortals.service;

import java.util.List;

import ro.immortals.model.History;

public interface HistoryService {

	public int add(History history);

	public void update(History history);

	public void delete(Integer id);

	public List<History> getAll();

	public History getById(Integer id);

	public Integer getAllSizeSearchBy(String search);

	public List<History> getByPageSearch(String search, Integer offset, Integer nrOfRecords);
	
	public Integer getAllSizeFilterBySearch(String criteria, String search);

	public List<History> getByPageFilterBySearch(String criteria,
			String search, Integer offset, Integer nrOfRecords);

}
