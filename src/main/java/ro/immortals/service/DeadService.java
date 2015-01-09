package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Dead;

public interface DeadService {

	public int add(Dead dead);

	public void update(Dead dead);

	public void delete(Integer id);

	public List<Dead> getAll();

	public Dead getById(Integer id);
	
	public boolean checkDuplicate(Dead dead);

	public Integer getAllSearchBySize(String search);

	public List<Dead> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer recordsPerPage);

}
