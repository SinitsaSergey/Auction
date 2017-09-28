package by.intexsoft.auction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Status;
import by.intexsoft.auction.repository.StatusRepository;
import by.intexsoft.auction.service.StatusService;

@Service
public class StatusServiceImpl extends AbstractServiceEntityImpl<Status> implements StatusService {

	@Autowired
	private StatusRepository repository;
	
	@Override
	public Status getByStatus(String status) {
		return repository.findByStatus(status);
	}

}
