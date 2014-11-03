package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.Grave;

public interface GraveDAO {

	public void add(Grave grave);

	public void update(Grave grave);

	public void delete(Grave grave);

	public List<Grave> getAll();

	public Grave getByCode(String code);

}
