package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.DeadWithoutFamily;

public interface DeadWithoutFamilyDAO {

	public void add(DeadWithoutFamily deadWithoutFamily);

	public void update(DeadWithoutFamily deadWithoutFamily);

	public void delete(DeadWithoutFamily deadWithoutFamily);

	public List<DeadWithoutFamily> getAll();

	public DeadWithoutFamily getByCode(String code);

}
