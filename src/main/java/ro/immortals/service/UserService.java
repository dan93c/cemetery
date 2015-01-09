package ro.immortals.service;

import java.util.List;

import ro.immortals.model.User;

public interface UserService {

	public int add(User user, String username);

	public void update(User user, String username);

	public void delete(String username, String user);

	public List<User> getAll();

	public User getByUsername(String username);

	public boolean checkDuplicate(User user);

	public List<User> simulateSearchResult(String username);
}
