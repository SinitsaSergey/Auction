package by.intexsoft.auction.service;

import java.util.List;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.TradingDay;

public interface AuctionService extends AbstractEntityService<Auction> {
	
	List<Auction> getForDay (TradingDay day);
	
	Auction save(Auction auction, String status);
	
	Auction getByLot (Lot lot);

}
