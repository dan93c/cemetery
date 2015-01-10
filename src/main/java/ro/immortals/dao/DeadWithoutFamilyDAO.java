package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.DeadWithoutFamily;

public interface DeadWithoutFamilyDAO {

	public void add(DeadWithoutFamily deadWithoutFamily);

	public void update(DeadWithoutFamily deadWithoutFamily);

	public void delete(DeadWithoutFamily deadWithoutFamily);

	public List<DeadWithoutFamily> getAll();

	public DeadWithoutFamily getById(Integer id);
	
	public Integer getAllSearchBySize(String search);

	public List<DeadWithoutFamily> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords);

	DeadWithoutFamily getByGraveAndFuneralDate(String nrGrave, String funeralCertificate);


}
