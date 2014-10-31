package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.Cemetery;

public interface CemeteryDAO {

	public void add(Cemetery cemetery);

	public void update(Cemetery cemetery);

	public void delete(Cemetery cemetery);

	public List<Cemetery> getAll();

	public Cemetery getByCode(String code);

}
