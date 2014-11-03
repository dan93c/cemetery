package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.User;

public interface UserDAO {

	public void add(User user);

	public void update(User user);

	public void delete(User user);

	public List<User> getAll();

	public User getByCode(String code);

}
