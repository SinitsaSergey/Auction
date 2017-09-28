package by.intexsoft.auction.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.Bid;
import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.repository.AuctionRepository;
import by.intexsoft.auction.service.AuctionService;
import by.intexsoft.auction.service.BidService;
import by.intexsoft.auction.service.LotService;

@Service
public class AuctionServiceImpl extends AbstractServiceEntityImpl<Auction> implements AuctionService {
	
	@Autowired
	private AuctionRepository repository;
	
	@Autowired
	private LotService lotService;
	
	@Autowired
	private BidService bidService;
	
	@Override
	public Auction save (Auction auction, String status) {
		lotService.save(auction.lot, status);
		return repository.save(auction);
	}
	
	@Override
	public void delete (int id) {
		//Auction auction = repository.findOne(id);
		//auction.lot.status = statusService.getByStatus("registered");
		lotService.save(repository.findOne(id).lot, "registered");
		repository.delete(id);
	}

	@Override
	public List<Auction> getForDay(TradingDay day) {
		System.out.println(day.tradingDate);
		return repository.findByTradingDayOrderByStartTime(day);
	}

	@Override
	public Auction getByLot(Lot lot) {
		return repository.findByLot(lot);
	}

	@Override
	public BigDecimal placeBid(int auctionId, User user) {
		Auction auction = repository.findOne(auctionId);
		Bid bid = new Bid();
		bid.bidder = user;
		bid.value = auction.currentBid.add(auction.stepPrice);
		bid.bidTime = new Timestamp(new Date().getTime());
		bidService.save(bid);
		auction.currentBid = bid.value;
		auction.bidList.add(bid);
		repository.save(auction);
		return bid.value;
	}
}
