package ro.immortals.service;

import java.util.List;

import ro.immortals.model.ClaimBook;

public interface ClaimBookService {

	public int add(ClaimBook claimBook, String username);

	public void update(ClaimBook claimBook, String username);

	public void delete(Integer id, String username);

	public List<ClaimBook> getAll();

	public ClaimBook getById(Integer id);
	
	public Integer getAllSearchBySize(String search);

	public List<ClaimBook> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords);
}
