package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.GraveRequest;

public interface GraveRequestDAO {

	public void add(GraveRequest graveRequest);

	public void update(GraveRequest graveRequest);

	public void delete(GraveRequest graveRequest);

	public List<GraveRequest> getAll();

	public GraveRequest getById(Integer id);
	
	public GraveRequest getByNrInfocet(String nr);

}
