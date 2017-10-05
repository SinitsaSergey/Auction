package by.intexsoft.auction.service;

import java.math.BigDecimal;
import java.util.List;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;

public interface AuctionService extends AbstractEntityService<Auction> {
	
	List<Auction> getForDay (TradingDay day);
	
	Auction save(Auction auction, String status);
	
	BigDecimal placeBid(int auctionId, User user);

	void validIsNotExpired(int auctionId);
	
	void replaceFromQueue(Auction auction);

	boolean timeIsBusy(Auction auction);

	List<Auction> getOnSaleForDay(TradingDay tradingDay);

	Auction getByLot(int lotId);

	List<Auction> getByBidholder(User user);

}
