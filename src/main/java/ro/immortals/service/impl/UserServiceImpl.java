package ro.immortals.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.UserDAO;
import ro.immortals.model.User;
import ro.immortals.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public int add(User user) {
		if (checkDuplicate(user)) {
			userDAO.add(user);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	@Transactional
	public void delete(String username) {
		User user = userDAO.getByUsername(username);
		if (user != null) {
			userDAO.delete(user);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User getByUsername(String username) {
		return userDAO.getByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(User user) {
		User existingUser = userDAO.getByUsername(user.getUsername());
		if (existingUser != null) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> simulateSearchResult(String username) {

		List<User> result = new ArrayList<User>();
		List<User> users = userDAO.getAll();

		for (User user : users) {
			if (user.getUsername().contains(username)) {
				result.add(user);
			}
		}

		return result;
	}

}
