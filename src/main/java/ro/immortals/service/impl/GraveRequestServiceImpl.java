package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.GraveRequestDAO;
import ro.immortals.model.GraveRequest;
import ro.immortals.service.GraveRequestService;

@Service
public class GraveRequestServiceImpl implements GraveRequestService {

	@Autowired
	private GraveRequestDAO graveRequestDAO;

	@Override
	@Transactional
	public int add(GraveRequest graveRequest) {
		if (checkDuplicate(graveRequest)) {
			graveRequestDAO.add(graveRequest);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(GraveRequest graveRequest) {
		graveRequestDAO.update(graveRequest);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		GraveRequest graveRequest = graveRequestDAO.getById(id);
		if (graveRequest != null) {
			graveRequestDAO.delete(graveRequest);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<GraveRequest> getAll() {
		return graveRequestDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public GraveRequest getById(Integer id) {
		return graveRequestDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(GraveRequest graveRequest) {
		GraveRequest existingGraveRequest = graveRequestDAO.getByNrInfocet(graveRequest.getNrInfocet());
		if (existingGraveRequest != null && (existingGraveRequest.getId() != graveRequest.getId())) {
			return false;
		}
		return true;
	}

}
