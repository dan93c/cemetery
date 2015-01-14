package ro.immortals.service;

import java.util.List;

import ro.immortals.model.Plot;

public interface PlotService {

	public int add(Plot plot, String username);

	public int update(Plot plot, String username);

	public void delete(Integer id, String username);

	public List<Plot> getAll();

	public Plot getById(Integer id);

	public boolean checkDuplicate(Plot plot);

	public List<Plot> getAllByCemetery(Integer cId);
}
