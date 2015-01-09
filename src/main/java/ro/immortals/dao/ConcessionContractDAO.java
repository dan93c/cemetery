package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.ConcessionContract;

public interface ConcessionContractDAO {
	
	public void add(ConcessionContract concessionContract);

	public void update(ConcessionContract concessionContract);

	public void delete(ConcessionContract concessionContract);

	public List<ConcessionContract> getAll();

	public ConcessionContract getById(Integer id);
	
	public ConcessionContract getByCnp(String cnp);
	
	public Integer getAllSearchBySize(String search);

	public List<ConcessionContract> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords);
}
