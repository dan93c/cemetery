package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Grave;

public interface GraveService {

	public int add(Grave grave, String username);

	public int update(Grave grave, String username);

	public void delete(Integer id, String username);

	public List<Grave> getAll();

	public Grave getById(Integer id);

	public boolean checkDuplicate(Grave grave);

	boolean checkGraveExistence(Grave grave, Integer plotId, Integer cemeteryId);

	public List<Grave> getAllByPage(Integer offset, Integer nrOfRecords);

	public List<Grave> getAllByPageWithContractsAndDeads(Integer offset,
			Integer nrOfRecords);

	public List<Grave> getAllByPageOrderBySearch(String order, String search,
			Integer offset, Integer nrOfRecords);

	public Integer getAllSearchBySize(String search);

	public List<Grave> getAllMonumentsByPageOrderBySearch(String order,
			String search, Integer offset, Integer nrOfRecords);

	public Integer getAllMonumentsSearchBySize(String search);
}
