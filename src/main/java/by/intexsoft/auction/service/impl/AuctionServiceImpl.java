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
	public Auction save(Auction auction, String status) {
		lotService.save(auction.lot, status);
		return repository.save(auction);
	}

	@Override
	public void delete(int id) {
		Auction auction = repository.findOne(id);
		lotService.save(auction.lot, "registered");
		repository.delete(id);
	}

	@Override
	public List<Auction> getForDay(TradingDay day) {
		return repository.findByTradingDayOrderByStartTime(day);
	}

	@Override
	public List<Auction> getOnSaleForDay(TradingDay tradingDay) {
		return repository.findByTradingDayAndStartTimeAfterOrderByStartTime(tradingDay,
				new Date(new Date().getTime() - 600000));
	}

	@Override
	public Auction getByLot(int lotId) {
		Lot lot = lotService.find(lotId);
		return repository.findByLot(lot);
	}

	@Override
	public BigDecimal placeBid(int id, User user) {
		Auction auction = repository.findOne(id);
		Bid bid = new Bid();
		bid.bidder = user;
		bid.value = auction.currentBid.add(auction.stepPrice);
		bid.bidTime = new Timestamp(new Date().getTime());
		bidService.save(bid);
		auction.currentBid = bid.value;
		auction.bidTime = bid.bidTime;
		auction.bidholder = bid.bidder;
		auction.bidList.add(bid);
		if ((auction.finishTime.getTime() - new Date().getTime()) > 30000) {
			auction.finishTime = new Date(new Date().getTime() + 30000);
		}
		repository.save(auction);
		return bid.value;
	}

	@Override
	public void validIsNotExpired(int id) {
		Auction auction = repository.findOne(id);
		if (timeExpired(auction) && bidExist(auction)) {
			lotService.save(repository.findOne(id).lot, "saled");
		}
		if (timeIsOver(auction) && !bidExist(auction)) {
			lotService.save(repository.findOne(id).lot, "canceled");
		}
	}

	public void replaceFromQueue(Auction auction) {
		if (auction == null)
			return;
		List<Auction> auctions = repository.findByTradingDayAndStartTimeIsNull(auction.tradingDay);
		auctions.get(0).startTime = auction.startTime;
		auctions.get(0).finishTime = auction.finishTime;
		repository.save(auctions.get(0));
		lotService.save(auctions.get(0).lot, "onsale");
		repository.delete(auction);
	}

	private boolean timeExpired(Auction auction) {
		return (auction.finishTime.compareTo(new Date()) == -1);
	}

	private boolean bidExist(Auction auction) {
		return (!auction.currentBid.equals(auction.lot.startPrice));
	}

	private boolean timeIsOver(Auction auction) {
		return auction.startTime.getTime() + 60000 < new Date().getTime();
	}

	@Override
	public boolean timeIsBusy(Auction auction) {
		Date start = new Date(auction.startTime.getTime() + 1);
		Date end = new Date(auction.finishTime.getTime() - 1);
		List<Auction> auctions = repository.findByStartTimeBetween(start, end);
		List<Auction> auctions2 = repository.findByFinishTimeBetween(start, end);
		if (auctions.size() > 0 || auctions2.size() > 0)
			return true;
		return false;
	}

	@Override
	public List<Auction> getByBidholder(User user) {
		return repository.findByBidholder(user);
	}
}
