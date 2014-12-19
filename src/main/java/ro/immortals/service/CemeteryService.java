package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Cemetery;

public interface CemeteryService {

	public Integer add(Cemetery cemetery, String username);

	public Integer update(Cemetery cemetery, String username);

	public void delete(Integer id, String username);

	public List<Cemetery> getAll();

	public Cemetery getById(Integer id);

	public boolean checkDuplicate(Cemetery cemetery);
}
