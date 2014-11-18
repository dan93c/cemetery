package ro.immortals.service;

import java.util.List;

import ro.immortals.model.User;

public interface UserService {

	public int add(User user);

	public void update(User user);

	public void delete(String username);

	public List<User> getAll();

	public User getByUsername(String username);

	public boolean checkDuplicate(User user);

	public List<User> simulateSearchResult(String username);
}
