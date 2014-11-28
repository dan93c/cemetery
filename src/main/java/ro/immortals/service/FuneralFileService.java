package ro.immortals.service;

import java.util.List;

import ro.immortals.model.FuneralFile;

public interface FuneralFileService {

	public int add(FuneralFile funeralFile);

	public void update(FuneralFile funeralFile);

	public void delete(Integer id);

	public List<FuneralFile> getAll();

	public FuneralFile getById(Integer id);

	public boolean checkDuplicate(FuneralFile funeralFile);
}
