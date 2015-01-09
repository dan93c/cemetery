package ro.immortals.dao;

import java.util.Date;
import java.util.List;

import ro.immortals.model.Dead;

public interface DeadDAO {

	public void add(Dead dead);

	public void update(Dead dead);

	public void delete(Dead dead);

	public List<Dead> getAll();

	public Dead getById(Integer id);
	
	public Dead getByGraveAndFuneralDate(String nrGrave, Date funeralDate);
	
	public Integer getAllSearchBySize(String search);

	public List<Dead> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer recordsPerPage);

}
