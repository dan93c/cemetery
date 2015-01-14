package ro.immortals.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.ConcessionContractDAO;
import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.ConcessionContract;
import ro.immortals.model.History;
import ro.immortals.service.ConcessionContractService;

@Service
public class ConcessionContractServiceImpl implements ConcessionContractService {

	@Autowired
	private ConcessionContractDAO concessionContractDAO;
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired
	private UserDAO userDAO;
	private static final String MODIFIED_OBJECT = "Contract de concesiune";

	@Override
	@Transactional
	public int add(ConcessionContract concessionContract, String username) {
		if (checkDuplicate(concessionContract)) {
			Calendar c = Calendar.getInstance();
			concessionContract.setReleaseDate(c.getTime());
			Integer expiredYear = c.get(Calendar.YEAR) + concessionContract.getPeriod();
			c.set(Calendar.YEAR, expiredYear);
			concessionContract.setExpiredDate(c.getTime());
			concessionContractDAO.add(concessionContract);
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Adaugare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setDetails(concessionContract.toString());
			history.setModifiedObjectCode(concessionContract.getId().toString());
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int update(ConcessionContract concessionContract, String username, String updateDate) {
		if (checkDuplicate(concessionContract)) {
			if (updateDate != null) {
				Date expiredDate = getExpiredDate(concessionContract.getExpiredDate(), concessionContract.getPeriod());
				concessionContract.setExpiredDate(expiredDate);
				concessionContract.setUpdatedDate(new Date());
				System.out.println("-----------dsfs-----------------");
			}
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Modificare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(concessionContract.getId().toString());
			String details = setDetailsForHistory(concessionContract);
			if (!details.isEmpty()) {
				details = "Numarul: " + details;
				history.setDetails(details);
				concessionContractDAO.update(concessionContract);
				historyDAO.add(history);
			}
			return 0;
		}
		return 1;
	}

	private Date getExpiredDate(Date oldExpiredDate, Integer period) {
		Calendar c = Calendar.getInstance();
		if (oldExpiredDate.after(c.getTime())) {
			c.setTime(oldExpiredDate);
		}
		Integer expiredYear = c.get(Calendar.YEAR) + period;
		c.set(Calendar.YEAR, expiredYear);
		return c.getTime();
	}

	private String setDetailsForHistory(ConcessionContract concessionContract) {
		String details = "";
		ConcessionContract oldConcessionContract = concessionContractDAO.getById(concessionContract.getId());
		if (!oldConcessionContract.getFirstName().contentEquals(concessionContract.getFirstName())) {
			details = "Nume vechi:" + oldConcessionContract.getFirstName() + ", Nume nou:"
			        + concessionContract.getFirstName() + "\r\n";
		}
		if (!oldConcessionContract.getLastName().contentEquals(concessionContract.getLastName())) {
			details = details + "Prenume vechi:" + oldConcessionContract.getLastName() + ", Prenume nou:"
			        + concessionContract.getLastName() + "\r\n";
		}
		if (!oldConcessionContract.getAddress().contentEquals(concessionContract.getAddress())) {
			details = details + "Adresa veche:" + oldConcessionContract.getAddress() + ", Adresa noua:"
			        + concessionContract.getAddress() + "\r\n";
		}
		if (!oldConcessionContract.getCnp().contentEquals(concessionContract.getCnp())) {
			details = details + "CNP vechi:" + oldConcessionContract.getCnp() + ", CNP nou:"
			        + concessionContract.getCnp() + "\r\n";
		}
		if (!oldConcessionContract.getEmailAddress().contentEquals(concessionContract.getEmailAddress())) {
			details = details + "Email vechi:" + oldConcessionContract.getEmailAddress() + ", Email nou:"
			        + concessionContract.getEmailAddress() + "\r\n";
		}
		if (!oldConcessionContract.getReceiptNr().contentEquals(concessionContract.getReceiptNr())) {
			details = details + "Numarul vechi:" + oldConcessionContract.getReceiptNr() + ", Numarul nou:"
			        + concessionContract.getReceiptNr() + "\r\n";
		}
		if (concessionContract.getUpdatedDate() != null) {
			if (oldConcessionContract.getUpdatedDate() != null) {
				if (oldConcessionContract.getUpdatedDate().compareTo(concessionContract.getUpdatedDate()) != 0) {
					details = details + "Data reinnoirii veche:" + oldConcessionContract.getUpdatedDate().toString()
					        + ", Data reinnoirii noua:" + concessionContract.getUpdatedDate().toString() + "\r\n";
				}
			} else {
				details = details + "Reinnoire contract \r\n";
			}
		}

		if (oldConcessionContract.getUpdatedDate() != null) {
			if (oldConcessionContract.getUpdatedDate().compareTo(concessionContract.getUpdatedDate()) != 0) {
				details = details + "Data reinnoirii veche:" + oldConcessionContract.getUpdatedDate().toString()
				        + ", Data reinnoirii noua:" + concessionContract.getUpdatedDate().toString() + "\r\n";
			}
		}

		if (!oldConcessionContract.getGrave().getId().equals(concessionContract.getGrave().getId())) {
			details = details + "Mormant vechi:" + oldConcessionContract.getGrave().getNrGrave() + ", Mormant nou:"
			        + concessionContract.getGrave().getNrGrave() + "\r\n";
		}
		return details;
	}

	@Override
	@Transactional
	public void delete(Integer id, String username) {
		ConcessionContract concessionContract = concessionContractDAO.getById(id);
		if (concessionContract != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(concessionContract.getId().toString());
			history.setDetails("");
			concessionContractDAO.delete(concessionContract);
			historyDAO.add(history);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ConcessionContract> getAll() {
		return concessionContractDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ConcessionContract getById(Integer id) {
		return concessionContractDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(ConcessionContract concessionContract) {
		ConcessionContract existingConcessionContract = concessionContractDAO.getByCnp(concessionContract.getCnp());
		if (existingConcessionContract != null && (existingConcessionContract.getId() != concessionContract.getId())) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSearchBySize(String search) {
		return concessionContractDAO.getAllSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ConcessionContract> getAllByPageOrderBySearch(String order, String search, Integer offset,
	        Integer nrOfRecords) {
		return concessionContractDAO.getAllByPageOrderBySearch(order, search, offset, nrOfRecords);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ConcessionContract> getAllGravesExpiredOnYears(String order, String search, Integer offset,
	        Integer nrOfRecords) {
		if (!isYear(search)) {
			return concessionContractDAO.getAllGravesExpiredOnYears(null, Calendar.getInstance().getTime(), order,
			        offset, nrOfRecords);
		}
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, Integer.parseInt(search));
		start.set(Calendar.MONTH, 0);
		start.set(Calendar.DAY_OF_MONTH, 1);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, Integer.parseInt(search) + 1);
		end.set(Calendar.MONTH, 0);
		end.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println("--------------------tryt--------------"
		        + concessionContractDAO.getAllGravesExpiredOnYears(start.getTime(), end.getTime(), order, offset,
		                nrOfRecords).size());
		return concessionContractDAO.getAllGravesExpiredOnYears(start.getTime(), end.getTime(), order, offset,
		        nrOfRecords);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllGravesExpiredOnYearsSize(String search) {
		if (!isYear(search)) {
			return concessionContractDAO.getAllGravesExpiredOnYearsSize(null, Calendar.getInstance().getTime());
		}
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, Integer.parseInt(search));
		start.set(Calendar.MONTH, 0);
		start.set(Calendar.DAY_OF_MONTH, 1);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, Integer.parseInt(search) + 1);
		end.set(Calendar.MONTH, 0);
		end.set(Calendar.DAY_OF_MONTH, 1);

		return concessionContractDAO.getAllGravesExpiredOnYearsSize(start.getTime(), end.getTime());
	}

	private static boolean isYear(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		if (str.length() > 4) {
			return false;
		}
		Integer year = Integer.parseInt(str);
		if (year <= 1930 || year >= 3000) {
			return false;
		}
		return true;
	}
}
