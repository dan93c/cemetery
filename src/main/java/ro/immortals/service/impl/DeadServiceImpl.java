package ro.immortals.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.DeadDAO;
import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.Dead;
import ro.immortals.model.History;
import ro.immortals.service.DeadService;

@Service
public class DeadServiceImpl implements DeadService {

	@Autowired
	private DeadDAO deadDAO;
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired
	private UserDAO userDAO;
	private static final String MODIFIED_OBJECT = "Decedat";

	@Override
	@Transactional
	public int add(Dead dead, String username) {
		if (checkDuplicate(dead)) {
			deadDAO.add(dead);
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Adaugare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setDetails(dead.toString());
			history.setModifiedObjectCode(dead.getId().toString());
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int update(Dead dead, String username) {
		if (checkDuplicate(dead)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Modificare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(dead.getId().toString());
			String details = setDetailsForHistory(dead);
			history.setDetails(details);
			deadDAO.update(dead);
			historyDAO.add(history);
			return 0;
		}
		return 1;

	}

	private String setDetailsForHistory(Dead dead) {
		String details = "";
		Dead oldDead = deadDAO.getById(dead.getId());
		if (!oldDead.getFirstName().contentEquals(dead.getFirstName())) {
			details = "Nume vechi:" + oldDead.getFirstName() + ", Nume nou:" + dead.getFirstName() + "\r\n";
		}
		if (!oldDead.getLastName().contentEquals(dead.getLastName())) {
			details = details + "Prenume vechi:" + oldDead.getLastName() + ", Prenume nou:" + dead.getLastName()
					+ "\r\n";
		}
		if (oldDead.getFuneralDate().compareTo(dead.getFuneralDate()) != 0) {
			details = details + "Data inmormantarii veche:" + oldDead.getFuneralDate().toString()
					+ ", Data inmormantarii noua:" + dead.getFuneralDate().toString() + "\r\n";
		}
		if (!oldDead.getGrave().getId().equals(dead.getGrave().getId())) {
			details = details + "Mormant vechi:" + oldDead.getGrave().getNrGrave() + ", Mormant nou:"
					+ dead.getGrave().getNrGrave() + "\r\n";
		}
		if (!oldDead.getReligion().contentEquals(dead.getReligion())) {
			details = details + "Religia veche:" + oldDead.getReligion() + ", Religia noua:" + dead.getReligion()
					+ "\r\n";
		}
		return details;
	}

	@Override
	@Transactional
	public void delete(Integer id, String username) {
		Dead dead = deadDAO.getById(id);
		if (dead != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(dead.getId().toString());
			history.setDetails("");
			deadDAO.delete(dead);
			historyDAO.add(history);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dead> getAll() {
		return deadDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Dead getById(Integer id) {
		return deadDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(Dead dead) {
		Dead existingDead = deadDAO.getByGraveAndFuneralDate(dead.getGrave().getNrGrave(), dead.getFuneralDate());
		if (existingDead != null && (existingDead.getId() != dead.getId())) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSearchBySize(String search) {
		return deadDAO.getAllSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dead> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer recordsPerPage) {
		return deadDAO.getAllByPageOrderBySearch(order, search, offset, recordsPerPage);
	}

}
