package by.intexsoft.auction.service;

import by.intexsoft.auction.model.Status;

public interface StatusService extends AbstractEntityService<Status> {
	
	Status getByStatus (String status);
}
