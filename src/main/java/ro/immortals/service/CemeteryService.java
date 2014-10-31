package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Cemetery;

public interface CemeteryService {

	public int add(Cemetery cemetery);

	public void update(Cemetery cemetery);

	public void delete(String code);

	public List<Cemetery> getAll();

	public Cemetery getByCode(String code);

	public boolean checkDuplicate(Cemetery cemetery);
}
