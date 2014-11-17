package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Plot;

public interface PlotService {

	public int add(Plot plot);

	public void update(Plot plot);

	public void delete(Integer id);

	public List<Plot> getAll();

	public Plot getById(Integer id);

	public boolean checkDuplicate(Plot plot);
}
