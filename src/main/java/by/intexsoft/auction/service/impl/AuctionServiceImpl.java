package by.intexsoft.auction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.TradingDay;
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
	public Auction save (Auction auction, String status) {
		System.out.println();
		System.out.println("save "+auction);
		System.out.println();
		auction.lot.status = statusService.getByStatus(status);
		lotService.save(auction.lot);
		return repository.save(auction);
	}
	
	@Override
	public void delete (int id) {
		Auction auction = repository.findOne(id);
		auction.lot.status = statusService.getByStatus("registered");
		lotService.save(auction.lot);
		repository.delete(id);
	}

	@Override
	public List<Auction> getForDay(TradingDay day) {
		System.out.println(day.tradingDate);
		return repository.findByTradingDayOrderByStartTime(day);
	}
}
