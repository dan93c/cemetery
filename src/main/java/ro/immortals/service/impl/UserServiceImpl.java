package ro.immortals.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.User;
import ro.immortals.model.History;
import ro.immortals.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private HistoryDAO historyDAO;
	private static final String MODIFIED_OBJECT = "Utilizator";

	@Override
	@Transactional
	public int add(User user, String username) {
		if (checkDuplicate(user)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Adaugare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setDetails(user.toString());
			userDAO.add(user);
			history.setModifiedObjectCode(user.getUsername());
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(User user, String username) {
		History history = new History();
		history.setUser(userDAO.getByUsername(username));
		history.setActionName("Modificare");
		history.setModificationDate(Calendar.getInstance().getTime());
		history.setModifiedObject(MODIFIED_OBJECT);
		history.setModifiedObjectCode(user.getUsername());
		history.setDetails("Parola");
		userDAO.update(user);
		historyDAO.add(history);
	}

	@Override
	@Transactional
	public void delete(String username, String u) {
		User user = userDAO.getByUsername(username);
		if (user != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(u));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(user.getUsername());
			history.setDetails("");
			userDAO.delete(user);
			historyDAO.add(history);
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
