package ro.immortals.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.DeadWithoutFamilyDAO;
import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.Dead;
import ro.immortals.model.DeadWithoutFamily;
import ro.immortals.model.History;
import ro.immortals.service.DeadWithoutFamilyService;

@Service
public class DeadWithoutFamilyServiceImpl implements DeadWithoutFamilyService {

	@Autowired
	private DeadWithoutFamilyDAO deadWithoutFamilyDAO;
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired
	private UserDAO userDAO;
	private static final String MODIFIED_OBJECT = "Decedat fara apartinatori";

	@Override
	@Transactional
	public int add(DeadWithoutFamily deadWithoutFamily, String username) {
		if (checkDuplicate(deadWithoutFamily)) {
			deadWithoutFamilyDAO.add(deadWithoutFamily);
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Adaugare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setDetails(deadWithoutFamily.toString());
			history.setModifiedObjectCode(deadWithoutFamily.getId().toString());
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int update(DeadWithoutFamily deadWithoutFamily, String username) {
		if (checkDuplicate(deadWithoutFamily)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Modificare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(deadWithoutFamily.getId().toString());
			String details = setDetailsForHistory(deadWithoutFamily);
			history.setDetails(details);
			deadWithoutFamilyDAO.update(deadWithoutFamily);
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	private String setDetailsForHistory(DeadWithoutFamily deadWithoutFamily) {
		String details = "";

		DeadWithoutFamily oldDeadWithoutFamily = deadWithoutFamilyDAO.getById(deadWithoutFamily.getId());
		if (!oldDeadWithoutFamily.getFuneralCertificate().contentEquals(deadWithoutFamily.getFuneralCertificate())) {
			details = "Adeverinta de inhumare veche:" + oldDeadWithoutFamily.getFuneralCertificate()

			+ ", Adeverinta de inhumare noua:" + deadWithoutFamily.getFuneralCertificate() + "\r\n";
		}
		if (!oldDeadWithoutFamily.getImlRequest().contentEquals(deadWithoutFamily.getImlRequest())) {
			details = details + "Cererea IML veche:" + oldDeadWithoutFamily.getImlRequest() + ", Cererea IML noua:"
					+ deadWithoutFamily.getImlRequest() + "\r\n";
		}
		return details;
	}

	@Override
	@Transactional
	public void delete(Integer id, String username) {
		DeadWithoutFamily deadWithoutFamily = deadWithoutFamilyDAO.getById(id);
		if (deadWithoutFamily != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(deadWithoutFamily.getId().toString());
			history.setDetails("");
			deadWithoutFamilyDAO.delete(deadWithoutFamily);
			historyDAO.add(history);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DeadWithoutFamily> getAll() {
		return deadWithoutFamilyDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DeadWithoutFamily getById(Integer id) {
		return deadWithoutFamilyDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSearchBySize(String search) {
		return deadWithoutFamilyDAO.getAllSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DeadWithoutFamily> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords) {
		return deadWithoutFamilyDAO.getAllByPageOrderBySearch(order, search, offset, nrOfRecords);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(DeadWithoutFamily deadWithoutFamily) {
		DeadWithoutFamily existingDead = deadWithoutFamilyDAO.getByGraveAndFuneralDate(deadWithoutFamily.getGrave().getNrGrave(),
				deadWithoutFamily.getFuneralCertificate());
		if (existingDead != null && (existingDead.getId() != deadWithoutFamily.getId())) {
			return false;
		}
		return true;
	}

}
