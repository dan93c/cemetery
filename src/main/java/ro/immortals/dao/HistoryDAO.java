package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.History;

public interface HistoryDAO {

	public void add(History history);

	public void update(History history);

	public void delete(History history);

	public List<History> getAll();

	public History getByCode(String code);

}
