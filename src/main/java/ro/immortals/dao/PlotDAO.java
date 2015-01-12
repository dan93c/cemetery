package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.Plot;

public interface PlotDAO {

	public void add(Plot plot);

	public void update(Plot plot);

	public void delete(Plot plot);

	public List<Plot> getAll();

	public Plot getById(Integer id);

	public Plot getByNameAndCemetery(String name, Integer cId);

	public List<Plot> getByCemetery(Integer cId);

}
