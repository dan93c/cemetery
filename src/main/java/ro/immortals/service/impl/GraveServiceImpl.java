package ro.immortals.service.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.GraveDAO;
import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.Grave;
import ro.immortals.model.History;
import ro.immortals.service.GraveService;

@Service
public class GraveServiceImpl implements GraveService {

	@Autowired
	private GraveDAO graveDAO;
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired
	private UserDAO userDAO;
	private static final String MODIFIED_OBJECT = "Mormant";

	@Override
	@Transactional
	public int add(Grave grave, String username) {
		if (checkDuplicate(grave)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Adaugare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setDetails(grave.toString());
			graveDAO.add(grave);
			history.setModifiedObjectCode(grave.getId().toString());
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int update(Grave grave, String username) {
		if (checkDuplicate(grave)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Modificare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(grave.getId().toString());
			String details = setDetailsForHistory(grave);
			if (!details.isEmpty()) {
				history.setDetails(details);
				graveDAO.update(grave);
				historyDAO.add(history);
			}
			return 0;
		}
		return 1;
	}

	private String setDetailsForHistory(Grave grave) {
		String details = "";
		Grave oldGrave = graveDAO.getById(grave.getId());
		if (!oldGrave.getNrGrave().contentEquals(grave.getNrGrave())) {
			details = "Denumire veche:" + oldGrave.getNrGrave() + ", Denumire noua:" + grave.getNrGrave() + "\r\n";
		}
		if (!oldGrave.getSurface().contentEquals(grave.getSurface())) {
			details = details + "Suprafata veche:" + oldGrave.getSurface() + ", Suprafata noua:" + grave.getSurface()
			        + "\r\n";
		}
		if (!oldGrave.getType().contentEquals(grave.getType())) {
			if (oldGrave.getType() == null) {
				details = details + "Tipul vechi: -, Tipul nou:" + grave.getType() + "\r\n";
			} else {
				if (grave.getType() != null) {
					details = details + "Tipul vechi: " + oldGrave.getType() + ", Tipul nou:" + grave.getType()
					        + "\r\n";
				} else {
					details = details + "Tipul vechi: " + oldGrave.getType() + ", Tipul nou: - \r\n";

				}

			}
		}
		if (!oldGrave.getPhotoScanned().contentEquals(grave.getPhotoScanned())) {
			details = details + "Poza schimbata \r\n";
		}
		if (!oldGrave.getObservations().contentEquals(grave.getObservations())) {
			details = details + "Observatii vechi:" + oldGrave.getObservations() + ", Observatii noi:"
			        + grave.getObservations() + "\r\n";
		}
		if (!oldGrave.getPlot().getId().equals(grave.getPlot().getId())) {
			details = details + "Parcela veche:" + oldGrave.getPlot().getName() + "\r\n";
		}
		if (!oldGrave.getPlot().getCemetery().getId().equals(grave.getPlot().getCemetery().getId())) {
			details = details + "Cimitirul vechi:" + oldGrave.getPlot().getCemetery().getName() + "\r\n";
		}
		return details;
	}

	@Override
	@Transactional
	public void delete(Integer id, String username) {
		Grave grave = graveDAO.getById(id);
		if (grave != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(grave.getId().toString());
			history.setDetails("");
			graveDAO.delete(grave);
			historyDAO.add(history);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAll() {
		return graveDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Grave getById(Integer id) {
		return graveDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(Grave grave) {
		Grave existingGrave = graveDAO.getByNumberAndPlot(grave.getNrGrave(), grave.getPlot().getId());
		if (existingGrave != null && (existingGrave.getId() != grave.getId())) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkGraveExistence(Grave grave, Integer plotId, Integer graveId) {
		List<Grave> graves = graveDAO.getAll();
		for (Grave g : graves) {
			if (g.getId() == grave.getId() && g.getPlot().getId() == plotId
			        && g.getPlot().getCemetery().getId() == graveId) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllByPage(Integer offset, Integer nrOfRecords) {
		return graveDAO.getAllByPage(offset, nrOfRecords);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllByPageWithContractsAndDeads(Integer offset, Integer nrOfRecords) {
		List<Grave> graves = graveDAO.getAllByPage(offset, nrOfRecords);
		for (Grave g : graves) {
			Hibernate.initialize(g.getConcessionContracts());
			Hibernate.initialize(g.getDeads());
			Hibernate.initialize(g.getDeadsWithoutFamily());
		}
		return graves;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords) {
		List<Grave> graves = graveDAO.getAllByPageOrderBySearch(order, search, offset, nrOfRecords);
		for (Grave g : graves) {
			Hibernate.initialize(g.getConcessionContracts());
			Hibernate.initialize(g.getDeads());
			Hibernate.initialize(g.getDeadsWithoutFamily());
		}
		return graves;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllSearchBySize(String search) {
		return graveDAO.getAllSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllMonumentsByPageOrderBySearch(String order, String search, Integer offset,
	        Integer nrOfRecords) {
		List<Grave> graves = graveDAO.getAllMonumentsByPageOrderBySearch(order, search, offset, nrOfRecords);
		for (Grave g : graves) {
			Hibernate.initialize(g.getConcessionContracts());
			Hibernate.initialize(g.getDeads());
			Hibernate.initialize(g.getDeadsWithoutFamily());
		}
		return graves;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAllMonumentsSearchBySize(String search) {
		return graveDAO.getAllMonumentsSearchBySize(search);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grave> getAllByPlotId(Integer id) {
		return graveDAO.getAllByPlotId(id);
	}
}
