package ro.immortals.service;

import java.util.List;

import ro.immortals.model.ConcessionContract;

public interface ConcessionContractService {

	public int add(ConcessionContract concessionContract);

	public void update(ConcessionContract concessionContract);

	public void delete(Integer id);

	public List<ConcessionContract> getAll();

	public ConcessionContract getById(Integer id);

	public boolean checkDuplicate(ConcessionContract concessionContract);

	public Integer getAllSearchBySize(String search);

	public List<ConcessionContract> getAllByPageOrderBySearch(String order, String search, Integer offset,
	        Integer nrOfRecords);
}
