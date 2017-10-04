package by.intexsoft.auction.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
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
	public Auction getByLot(Lot lot) {
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
		auction.bidList.add(bid);
		auction.finishTime.setTime(new Date().getTime() + 30000);
		repository.save(auction);
		return bid.value;
	}

	@Override
	public void validIsNotExpired(int id) {
		Auction auction = repository.findOne(id);
		if (timeExpired(auction) && bidExist(auction)) {
			lotService.save(repository.findOne(id).lot, "saled");
			shiftTime(auction);
		}
		if (timeIsOver(auction) && !bidExist(auction)) {
			lotService.save(repository.findOne(id).lot, "canceled");
			shiftTime(auction);
		}
		/*if (noRecentBids(auction) && bidExist(auction)) {
			lotService.save(repository.findOne(id).lot, "saled");
			shiftTime(auction);
			}*/
	}
	
	private void shiftTime(Auction auction) {
		/*List <Auction> auctionsForDay = getForDay(auction.tradingDay);
		List <Auction> auctionsOnSale = new LinkedList<Auction>();
		for (int i=1; i<auctionsForDay.size()-1; i++) {
			if (!auctionsForDay.get(i).lot.status.status.equals("onsale")) break;
			Timestamp finishThis = auctionsForDay.get(i).finishTime;
			Timestamp startNext = auctionsForDay.get(i+1).startTime;
			long interval = startNext.getTime() - finishThis.getTime();
			auctionsForDay.get(i+1).startTime = auctionsForDay.get(i).finishTime;
			Calendar finish = Calendar.getInstance();
			finish.setTime(new Date());
			auctionsForDay.get(i+1).finishTime = finish;
		}*/
		
	}

	private boolean timeExpired (Auction auction) {
		return (auction.finishTime.compareTo(new Date()) == -1);
	}
	
	private boolean bidExist (Auction auction) {
		return (!auction.currentBid.equals(auction.lot.startPrice));
	}
	
	private boolean timeIsOver (Auction auction) {
		return auction.startTime.getTime() + 60000 < new Date().getTime();
	}
	
	/*private boolean noRecentBids (Auction auction) {
		return (new Date().getTime() - auction.bidTime.getTime()>30000);
	}*/
}
