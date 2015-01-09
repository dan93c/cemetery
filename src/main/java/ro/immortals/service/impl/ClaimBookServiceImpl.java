package ro.immortals.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.ClaimBookDAO;
import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.ClaimBook;
import ro.immortals.model.History;
import ro.immortals.service.ClaimBookService;

@Service
public class ClaimBookServiceImpl implements ClaimBookService {

	@Autowired
	private ClaimBookDAO claimBookDAO;
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired
	private UserDAO userDAO;
	private static final String MODIFIED_OBJECT = "Reclamatii";

	@Override
	@Transactional
	public int add(ClaimBook claimBook, String username) {
		claimBookDAO.add(claimBook);
		History history = new History();
		history.setUser(userDAO.getByUsername(username));
		history.setActionName("Adaugare");
		history.setModificationDate(Calendar.getInstance().getTime());
		history.setModifiedObject(MODIFIED_OBJECT);
		history.setDetails(claimBook.toString());
		history.setModifiedObjectCode(claimBook.getId().toString());
		historyDAO.add(history);
		return 1;
	}

	@Override
	@Transactional
	public void update(ClaimBook claimBook, String username) {
		History history = new History();
		history.setUser(userDAO.getByUsername(username));
		history.setActionName("Modificare");
		history.setModificationDate(Calendar.getInstance().getTime());
		history.setModifiedObject(MODIFIED_OBJECT);
		history.setModifiedObjectCode(claimBook.getId().toString());
		String details = setDetailsForHistory(claimBook);
		history.setDetails(details);
		claimBookDAO.update(claimBook);
		historyDAO.add(history);
	}

	private String setDetailsForHistory(ClaimBook claimBook) {
		String details = "";
		ClaimBook oldClaimBook = claimBookDAO.getById(claimBook.getId());
		if (!oldClaimBook.getComplainer().contentEquals(
				claimBook.getComplainer())) {
			details = "Autor vechi:" + oldClaimBook.getComplainer()
					+ ", Autor nou:" + claimBook.getComplainer() + "\r\n";
		}
		if (!oldClaimBook.getClaims().contentEquals(claimBook.getClaims())) {
			details = details + "Reclamatii vechi:" + oldClaimBook.getClaims()
					+ ", Reclamatii noi:" + claimBook.getClaims() + "\r\n";
		}
		return details;
	}

	@Override
	@Transactional
	public void delete(Integer id, String username) {
		ClaimBook claimBook = claimBookDAO.getById(id);
		if (claimBook != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(claimBook.getId().toString());
			history.setDetails("");
			claimBookDAO.delete(claimBook);
			historyDAO.add(history);

		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClaimBook> getAll() {
		return claimBookDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ClaimBook getById(Integer id) {
		return claimBookDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSearchBySize(String search) {
		return claimBookDAO.getAllSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClaimBook> getAllByPageOrderBySearch(String order,
			String search, Integer offset, Integer nrOfRecords) {
		return claimBookDAO.getAllByPageOrderBySearch(order, search, offset,
				nrOfRecords);
	}

}
