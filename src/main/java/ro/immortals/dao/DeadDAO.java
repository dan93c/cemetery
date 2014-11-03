package ro.immortals.dao;

import java.util.List;

import ro.immortals.model.Dead;

public interface DeadDAO {

	public void add(Dead dead);

	public void update(Dead dead);

	public void delete(Dead dead);

	public List<Dead> getAll();

	public Dead getByCode(String code);

}