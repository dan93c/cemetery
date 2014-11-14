package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Cemetery;

public interface CemeteryService {

	public int add(Cemetery cemetery);

	public void update(Cemetery cemetery);

	public void delete(Integer id);

	public List<Cemetery> getAll();

	public Cemetery getById(Integer id);

	public boolean checkDuplicate(Cemetery cemetery);
}
