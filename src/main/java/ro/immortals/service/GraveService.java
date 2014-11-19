package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Grave;

public interface GraveService {

	public int add(Grave grave);

	public void update(Grave grave);

	public void delete(Integer id);

	public List<Grave> getAll();

	public Grave getById(Integer id);

	public boolean checkDuplicate(Grave grave);

	boolean checkGraveExistence(Grave grave, Integer plotId, Integer cemeteryId);
}
