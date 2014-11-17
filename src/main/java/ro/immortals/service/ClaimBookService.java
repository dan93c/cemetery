package ro.immortals.service;

import java.util.List;

import ro.immortals.model.ClaimBook;

public interface ClaimBookService {

	public int add(ClaimBook claimBook);

	public void update(ClaimBook claimBook);

	public void delete(Integer id);

	public List<ClaimBook> getAll();

	public ClaimBook getById(Integer id);
}
