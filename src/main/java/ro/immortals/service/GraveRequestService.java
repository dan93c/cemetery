package ro.immortals.service;

import java.util.List;

import ro.immortals.model.GraveRequest;

public interface GraveRequestService {

	public int add(GraveRequest graveRequest, String username);

	public void update(GraveRequest graveRequest, String username);

	public void delete(Integer id, String username);

	public List<GraveRequest> getAll();

	public GraveRequest getById(Integer id);

	public boolean checkDuplicate(GraveRequest graveRequest);

	public Integer getAllSearchBySize(String search);

	public List<GraveRequest> getAllByPageOrderBySearch(String order,
			String search, Integer offset, Integer nrOfRecords);
}
