package ro.immortals.service;

import java.util.List;

import ro.immortals.model.GraveRequest;

public interface GraveRequestService {

	public int add(GraveRequest graveRequest);

	public void update(GraveRequest graveRequest);

	public void delete(Integer id);

	public List<GraveRequest> getAll();

	public GraveRequest getById(Integer id);

	public boolean checkDuplicate(GraveRequest graveRequest);
}
