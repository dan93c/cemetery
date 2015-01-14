package ro.immortals.dao;

import java.util.Date;
import java.util.List;

import ro.immortals.model.ConcessionContract;

public interface ConcessionContractDAO {

	public void add(ConcessionContract concessionContract);

	public void update(ConcessionContract concessionContract);

	public void delete(ConcessionContract concessionContract);

	public List<ConcessionContract> getAll();

	public ConcessionContract getById(Integer id);

	public ConcessionContract getByExpirationDateGrave(Integer graveId);

	public Integer getAllSearchBySize(String search);

	public List<ConcessionContract> getAllByPageOrderBySearch(String order,
			String search, Integer offset, Integer nrOfRecords);

//	public List<ConcessionContract> getAllGravesExpiredOnYears(String order,
//			String search, Integer offset, Integer nrOfRecords);
//
//	public Integer getAllGravesExpiredOnYearsSize(String search);

	public List<ConcessionContract> getAllGravesExpiredOnYears(Date start, Date end, String order, Integer offset,
            Integer nrOfRecords);

	public Integer getAllGravesExpiredOnYearsSize(Date start, Date end);
}
