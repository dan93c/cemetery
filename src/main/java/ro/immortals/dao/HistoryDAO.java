package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.History;

public interface HistoryDAO {

	public void add(History history);

	public void update(History history);

	public void delete(History history);

	public List<History> getAll();

	public History getById(Integer id);

	public List<History> getAllByPage(Integer offset, Integer nrOfRecords);

	public List<History> getByPageSearch(String search, Integer offset,
			Integer nrOfRecords);

	public Integer getAllSizeSearchBy(String search);

	public Integer getAllSizeFilterBySearch(String criteria, String search);

	public List<History> getByPageFilterBySearch(String criteria,
			String search, Integer offset, Integer nrOfRecords);

}
