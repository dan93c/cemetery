package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.PlotDAO;
import ro.immortals.model.Plot;
import ro.immortals.service.PlotService;

@Service
public class PlotServiceImpl implements PlotService {

	@Autowired
	private PlotDAO plotDAO;

	@Override
	@Transactional
	public int add(Plot plot) {
		if (checkDuplicate(plot)) {
			plotDAO.add(plot);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(Plot plot) {
		plotDAO.update(plot);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Plot plot = plotDAO.getById(id);
		if (plot != null) {
			plotDAO.delete(plot);
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
		Plot existingPlot = plotDAO.getByNameAndCemetery(plot.getName(), plot.getCemetery().getId());
		if (existingPlot != null && (existingPlot.getId() != plot.getId())) {
			return false;
		}
		return true;
	}

}
