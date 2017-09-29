package by.intexsoft.auction.service;

import java.math.BigDecimal;
import java.util.List;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;

public interface AuctionService extends AbstractEntityService<Auction> {
	
	List<Auction> getForDay (TradingDay day);
	
	Auction save(Auction auction, String status);
	
	Auction getByLot (Lot lot);

	BigDecimal placeBid(int auctionId, User user);

	void validIsNotExpired(int auctionId);
	
}
