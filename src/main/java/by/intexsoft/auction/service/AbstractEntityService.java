package by.intexsoft.auction.service;

import java.util.List;

import by.intexsoft.auction.model.AbstractEntity;

public interface AbstractEntityService <T extends AbstractEntity> {
	
	T find(int id);
	
	List<T> findAll();
	
	T save(T entity);
	
	void delete(int id);
	
}
