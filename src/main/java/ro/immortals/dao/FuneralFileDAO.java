package ro.immortals.dao;

import java.util.Date;
import java.util.List;

import ro.immortals.model.FuneralFile;

public interface FuneralFileDAO {

	public void add(FuneralFile funeralFile);

	public void update(FuneralFile funeralFile);

	public void delete(FuneralFile funeralFile);

	public List<FuneralFile> getAll();

	public FuneralFile getById(Integer id);

	public FuneralFile getByGraveAndFuneralDate(String nrGrave, Date funeralDate);
}
