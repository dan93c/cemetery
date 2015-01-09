package ro.immortals.service;

import java.util.List;

import ro.immortals.model.ConcessionContract;

public interface ConcessionContractService {

	public int add(ConcessionContract concessionContract, String username);

	public void update(ConcessionContract concessionContract, String username);

	public void delete(Integer id, String username);

	public List<ConcessionContract> getAll();

	public ConcessionContract getById(Integer id);

	public boolean checkDuplicate(ConcessionContract concessionContract);

	public Integer getAllSearchBySize(String search);

	public List<ConcessionContract> getAllByPageOrderBySearch(String order, String search, Integer offset,
	        Integer nrOfRecords);
}
