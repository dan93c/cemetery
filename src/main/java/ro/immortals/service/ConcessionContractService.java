package ro.immortals.service;

import java.util.List;

import ro.immortals.model.ConcessionContract;
import ro.immortals.model.Grave;

public interface ConcessionContractService {

	public int add(ConcessionContract concessionContract);

	public void update(ConcessionContract concessionContract);

	public void delete(Integer id);

	public List<ConcessionContract> getAll();

	public ConcessionContract getById(Integer id);

	public boolean checkDuplicate(ConcessionContract concessionContract);
}
