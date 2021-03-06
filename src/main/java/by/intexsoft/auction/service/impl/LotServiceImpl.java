package by.intexsoft.auction.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.Status;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.repository.LotRepository;
import by.intexsoft.auction.service.AuctionService;
import by.intexsoft.auction.service.LotService;
import by.intexsoft.auction.service.StatusService;

@Service
public class LotServiceImpl extends AbstractServiceEntityImpl<Lot> implements LotService {

	@Autowired
	private LotRepository repository;

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private AuctionService auctionService;
	

	@Override
	public Lot save(Lot lot, String status) {
		if (lot.getId()==null) lot.added = new Timestamp (new Date().getTime());
		lot.status = statusService.getByStatus(status);
		return repository.save(lot);
	}

	@Override
	public List<Lot> getByStatus(Status status) {
		return repository.findByStatus(status);
	}

	@Override
	public List<Lot> getFreeLots() {
		List<Lot> regLots = repository.findByStatus(statusService.getByStatus("registered"));
		List<Lot> queueLots = repository.findByStatus(statusService.getByStatus("queue"));
		List<Lot> result = new ArrayList<Lot>(regLots);
		result.addAll(queueLots);
		return result;
	}

	@Override
	public List<Lot> getByUser(User user) {
		List<Lot> lots = repository.findBySeller(user);
		for (Iterator<Lot> i = lots.iterator(); i.hasNext();) {
			Lot lot = i.next();
			lot.auction = auctionService.getByLot(lot.getId());
		}
		return lots;
	}
	
	@Override
	public List<Lot> getPurchasedByUser(User user) {
		List<Auction> auctions = auctionService.getByBidholder(user);
		List <Lot> lots = new LinkedList<>();
		for (Iterator<Auction> i = auctions.iterator(); i.hasNext();) {
			Auction auction = i.next();
			lots.add(auction.lot);
		}
		return lots; 
	}
	
	@Override
	public void delete (int id) {
		Lot lot = repository.findOne(id);
		Auction auction = auctionService.getByLot(lot.getId());
		auctionService.replaceFromQueue(auction);
		//if (auction != null) auctionService.delete(auction.getId());
		repository.delete(id);
	}

}
