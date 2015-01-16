package ro.immortals.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.GraveRequestDAO;
import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.GraveRequest;
import ro.immortals.model.History;
import ro.immortals.service.GraveRequestService;

@Service
public class GraveRequestServiceImpl implements GraveRequestService {

	@Autowired
	private GraveRequestDAO graveRequestDAO;
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired
	private UserDAO userDAO;
	private static final String MODIFIED_OBJECT = "Cerere loc de veci";

	@Override
	@Transactional
	public int add(GraveRequest graveRequest, String username) {
		if (checkDuplicate(graveRequest)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Adaugare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setDetails(graveRequest.toString());
			graveRequest.setRegistrationDate(Calendar.getInstance().getTime());
			graveRequestDAO.add(graveRequest);
			history.setModifiedObjectCode(graveRequest.getId().toString());
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int update(GraveRequest graveRequest, String username) {
		if (checkDuplicate(graveRequest)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Modificare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(graveRequest.getId().toString());
			String details = setDetailsForHistory(graveRequest);
			history.setDetails(details);
			graveRequest.setRegistrationDate(Calendar.getInstance().getTime());
			System.out.println("------------------service");
			graveRequestDAO.update(graveRequest);
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	private String setDetailsForHistory(GraveRequest graveRequest) {
		String details = "";
		GraveRequest oldGraveRequest = graveRequestDAO.getById(graveRequest.getId());
		if (!oldGraveRequest.getNrInfocet().contentEquals(graveRequest.getNrInfocet())) {
			details = "Nr infocet vechi:" + oldGraveRequest.getNrInfocet() + ", Nr infocet nou:" + graveRequest.getNrInfocet()
					+ "\r\n";
		}
		if (!oldGraveRequest.getSolvingStage().contentEquals(graveRequest.getSolvingStage())) {
			details = details + "Stagiul de solutionare vechi:" + oldGraveRequest.getSolvingStage()
					+ ", Stagiul de solutionare nou:" + graveRequest.getSolvingStage() + "\r\n";
		}
		return details;
	}

	@Override
	@Transactional
	public void delete(Integer id, String username) {
		GraveRequest graveRequest = graveRequestDAO.getById(id);
		if (graveRequest != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(graveRequest.getId().toString());
			history.setDetails("");
			graveRequestDAO.delete(graveRequest);
			historyDAO.add(history);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<GraveRequest> getAll() {
		return graveRequestDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public GraveRequest getById(Integer id) {
		return graveRequestDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(GraveRequest graveRequest) {
		GraveRequest existingGraveRequest = graveRequestDAO.getByNrInfocet(graveRequest.getNrInfocet());
		if (existingGraveRequest != null && (existingGraveRequest.getId() != graveRequest.getId())) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSearchBySize(String search) {
		return graveRequestDAO.getAllSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GraveRequest> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords) {
		return graveRequestDAO.getAllByPageOrderBySearch(order, search, offset, nrOfRecords);
	}

}
