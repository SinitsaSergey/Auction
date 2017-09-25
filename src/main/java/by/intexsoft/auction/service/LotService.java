package by.intexsoft.auction.service;

import java.util.List;

import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.Status;

public interface LotService extends AbstractEntityService<Lot> {
	
	List<Lot> getByStatus (Status status);
	
	List<Lot> getFreeLots ();

}
