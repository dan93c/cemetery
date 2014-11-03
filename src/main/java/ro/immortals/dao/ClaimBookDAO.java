package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.ClaimBook;

public interface ClaimBookDAO {

	public void add(ClaimBook claimBook);

	public void update(ClaimBook claimBook);

	public void delete(ClaimBook claimBook);

	public List<ClaimBook> getAll();

	public ClaimBook getByCode(String code);

}
