package ro.immortals.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.CemeteryDAO;
import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.Cemetery;
import ro.immortals.model.History;
import ro.immortals.service.CemeteryService;

@Service
public class CemeteryServiceImpl implements CemeteryService {

	@Autowired
	private CemeteryDAO cemeteryDAO;
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired
	private UserDAO userDAO;
	private static final String MODIFIED_OBJECT = "Cimitir";

	@Override
	@Transactional
	public Integer add(Cemetery cemetery, String username) {
		if (checkDuplicate(cemetery)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Adaugare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setDetails(cemetery.toString());
			cemeteryDAO.add(cemetery);
			history.setModifiedObjectCode(cemetery.getId().toString());
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public Integer update(Cemetery cemetery, String username) {
		if (checkDuplicate(cemetery)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Modificare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(cemetery.getId().toString());
			String details = setDetailsForHistory(cemetery);
			history.setDetails(details);
			cemeteryDAO.update(cemetery);
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	private String setDetailsForHistory(Cemetery cemetery) {
		String details = "";
		Cemetery oldCemetery = cemeteryDAO.getById(cemetery.getId());
		if (!oldCemetery.getName().contentEquals(cemetery.getName())) {
			details = "Denumire veche:" + oldCemetery.getName() + ", Denumire noua:" + cemetery.getName() + "\r\n";
		}
		if (!oldCemetery.getAddress().contentEquals(cemetery.getAddress())) {
			details = details + "Adresa veche:" + oldCemetery.getAddress() + ", Adresa noua:" + cemetery.getAddress()
			        + "\r\n";
		}
		return details;
	}

	@Override
	@Transactional
	public void delete(Integer id, String username) {
		Cemetery cemetery = cemeteryDAO.getById(id);
		if (cemetery != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(cemetery.getId().toString());
			history.setDetails("");
			cemeteryDAO.delete(cemetery);
			historyDAO.add(history);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cemetery> getAll() {
		return cemeteryDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cemetery getById(Integer id) {
		return cemeteryDAO.getById(id);
	}

	public Cemetery getByName(String name) {
		return cemeteryDAO.getByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(Cemetery cemetery) {
		Cemetery existingCemetery = cemeteryDAO.getByName(cemetery.getName());
		if (existingCemetery != null && (existingCemetery.getId() != cemetery.getId())) {
			return false;
		}
		return true;
	}

}
