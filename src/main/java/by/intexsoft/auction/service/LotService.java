package by.intexsoft.auction.service;

import java.util.List;

import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.Status;
import by.intexsoft.auction.model.User;

public interface LotService extends AbstractEntityService<Lot> {
	
	List<Lot> getByStatus (Status status);
	
	List<Lot> getFreeLots ();

	Lot save(Lot lot, String status);
	
	List<Lot> getByUser (User user);

}
