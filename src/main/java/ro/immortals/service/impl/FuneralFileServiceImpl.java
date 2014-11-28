package ro.immortals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.immortals.dao.FuneralFileDAO;
import ro.immortals.model.Dead;
import ro.immortals.model.FuneralFile;
import ro.immortals.service.FuneralFileService;

@Service
public class FuneralFileServiceImpl implements FuneralFileService {

	@Autowired
	private FuneralFileDAO funeralFileDAO;

	@Override
	@Transactional
	public int add(FuneralFile funeralFile) {
		if (checkDuplicate(funeralFile)) {
			funeralFile.getDead().setFuneralFile(funeralFile);
//			Dead dead = funeralFile.getDead();
//			dead.setFuneralFile(funeralFile);
			funeralFileDAO.add(funeralFile);
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void update(FuneralFile funeralFile) {
		funeralFile.getDead().setFuneralFile(funeralFile);
	//	System.out.println("---------------------------------------------"+dead.getId()+"     "+funeralFile.getId()+"      "+dead.getFuneralFile().getId());
		funeralFileDAO.update(funeralFile);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		FuneralFile funeralFile = funeralFileDAO.getById(id);
		if (funeralFile != null) {
			funeralFile.getDead().setFuneralFile(funeralFile);
			funeralFileDAO.delete(funeralFile);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<FuneralFile> getAll() {
		return funeralFileDAO.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public FuneralFile getById(Integer id) {
		return funeralFileDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkDuplicate(FuneralFile funeralFile) {
		FuneralFile existingFuneralFile =  funeralFileDAO.getByGraveAndFuneralDate(funeralFile.getGrave().getNrGrave(),funeralFile.getFuneralDate());
		if (existingFuneralFile != null && (existingFuneralFile.getId() != funeralFile.getId())) {
			return false;
		}
		return true;
	}

}
