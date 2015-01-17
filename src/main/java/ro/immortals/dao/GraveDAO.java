package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.Grave;

public interface GraveDAO {

	public void add(Grave grave);

	public void update(Grave grave);

	public void delete(Grave grave);

	public List<Grave> getAll();

	public Grave getById(Integer id);
	
	public List<Grave> getAllByPlotId(Integer id);

	public Grave getByNumberAndPlot (String number ,Integer plotId);

	public List<Grave> getAllByPage(Integer offset, Integer nrOfRecords);

	public List<Grave> getAllByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords);

	public Integer getAllSearchBySize(String search);
	
	public List<Grave> getAllMonumentsByPageOrderBySearch(String order, String search, Integer offset, Integer nrOfRecords);

	public Integer getAllMonumentsSearchBySize(String search);

}
