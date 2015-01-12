package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Dead;

public interface DeadService {

	public int add(Dead dead, String username);

	public int update(Dead dead, String username);

	public void delete(Integer id, String username);

	public List<Dead> getAll();

	public Dead getById(Integer id);

	public boolean checkDuplicate(Dead dead);

	public Integer getAllSearchBySize(String search);

	public List<Dead> getAllByPageOrderBySearch(String order, String search,
			Integer offset, Integer recordsPerPage);

}
