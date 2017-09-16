package by.intexsoft.auction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.repository.AuctionRepository;
import by.intexsoft.auction.service.AuctionService;
import by.intexsoft.auction.service.LotService;
import by.intexsoft.auction.service.StatusService;

@Service
public class AuctionServiceImpl extends AbstractServiceEntityImpl<Auction> implements AuctionService {
	
	@Autowired
	private AuctionRepository repository;
	
	@Autowired
	private LotService lotService;
	
	@Autowired
	private StatusService statusService;
	
	@Override
	public Auction save (Auction auction) {
		auction.lot.status = statusService.findByStatus("on sale");
		lotService.save(auction.lot);
		return repository.save(auction);
	}

	@Override
	public void delete (int id) {
		Auction auction = repository.findOne(id);
		auction.lot.status = statusService.findByStatus("registered");
		lotService.save(auction.lot);
		repository.delete(id);
	}
}
