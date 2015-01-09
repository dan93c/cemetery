package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.ClaimBook;

public interface ClaimBookDAO {

	public void add(ClaimBook claimBook);

	public void update(ClaimBook claimBook);

	public void delete(ClaimBook claimBook);

	public List<ClaimBook> getAll();

	public ClaimBook getById(Integer id);

	public Integer getAllSearchBySize(String search);

	public List<ClaimBook> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords);
}
