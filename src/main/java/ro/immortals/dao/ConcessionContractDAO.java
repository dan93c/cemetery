package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.ConcessionContract;
import ro.immortals.model.Grave;

public interface ConcessionContractDAO {
	
	public void add(ConcessionContract concessionContract);

	public void update(ConcessionContract concessionContract);

	public void delete(ConcessionContract concessionContract);

	public List<ConcessionContract> getAll();

	public ConcessionContract getById(Integer id);
	
	public ConcessionContract getByCnp(String cnp);
}
