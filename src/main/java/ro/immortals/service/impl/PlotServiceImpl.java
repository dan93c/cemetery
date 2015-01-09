package ro.immortals.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.HistoryDAO;
import ro.immortals.dao.PlotDAO;
import ro.immortals.dao.UserDAO;
import ro.immortals.model.History;
import ro.immortals.model.Plot;
import ro.immortals.service.PlotService;

@Service
public class PlotServiceImpl implements PlotService {

	@Autowired
	private PlotDAO plotDAO;
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired
	private UserDAO userDAO;
	private static final String MODIFIED_OBJECT = "Parcela";

	@Override
	@Transactional
	public int add(Plot plot, String username) {
		if (checkDuplicate(plot)) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Adaugare");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setDetails(plot.toString());
			plotDAO.add(plot);
			history.setModifiedObjectCode(plot.getId().toString());
			historyDAO.add(history);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(Plot plot, String username) {
		History history = new History();
		history.setUser(userDAO.getByUsername(username));
		history.setActionName("Modificare");
		history.setModificationDate(Calendar.getInstance().getTime());
		history.setModifiedObject(MODIFIED_OBJECT);
		history.setModifiedObjectCode(plot.getId().toString());
		String details = setDetailsForHistory(plot);
		history.setDetails(details);
		plotDAO.update(plot);
		historyDAO.add(history);
	}

	private String setDetailsForHistory(Plot plot) {
		String details = "";
		Plot oldPlot = plotDAO.getById(plot.getId());
		if (!oldPlot.getName().contentEquals(plot.getName())) {
			details = "Denumire veche:" + oldPlot.getName()
					+ ", Denumire noua:" + plot.getName() + "\r\n";
		}
		if (!oldPlot.getCemetery().getId().equals(plot.getCemetery().getId())) {
			details = details + "Cimitir vechi:"
					+ oldPlot.getCemetery().getName() + ", Cimitir nou:"
					+ plot.getCemetery().getName() + "\r\n";
		}
		return details;
	}

	@Override
	@Transactional
	public void delete(Integer id, String username) {
		Plot plot = plotDAO.getById(id);
		if (plot != null) {
			History history = new History();
			history.setUser(userDAO.getByUsername(username));
			history.setActionName("Stergere");
			history.setModificationDate(Calendar.getInstance().getTime());
			history.setModifiedObject(MODIFIED_OBJECT);
			history.setModifiedObjectCode(plot.getId().toString());
			history.setDetails("");
			plotDAO.delete(plot);
			historyDAO.add(history);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Plot> getAll() {
		return plotDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Plot getById(Integer id) {
		return plotDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(Plot plot) {
		Plot existingPlot = plotDAO.getByNameAndCemetery(plot.getName(), plot
				.getCemetery().getId());
		if (existingPlot != null && (existingPlot.getId() != plot.getId())) {
			return false;
		}
		return true;
	}

}
