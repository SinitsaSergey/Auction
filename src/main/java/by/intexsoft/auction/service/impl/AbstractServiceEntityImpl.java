package by.intexsoft.auction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.AbstractEntity;
import by.intexsoft.auction.service.AbstractEntityService;


public class AbstractServiceEntityImpl<T extends AbstractEntity> implements AbstractEntityService<T> {

	@Autowired
	JpaRepository <T, Integer> repository;
	
	@Override
	public T find(int id) {
		return repository.findOne(id);
	}

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public T save(T entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(int id) {
		repository.delete(id);
	}

}
