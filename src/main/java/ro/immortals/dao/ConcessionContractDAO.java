package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.ConcessionContract;

public interface ConcessionContractDAO {
	
	public void add(ConcessionContract concessionContract);

	public void update(ConcessionContract concessionContract);

	public void delete(ConcessionContract concessionContract);

	public List<ConcessionContract> getAll();

	public ConcessionContract getByCode(String code);
}
