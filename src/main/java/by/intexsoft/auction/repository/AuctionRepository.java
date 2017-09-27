package by.intexsoft.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.TradingDay;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {
	
	List<Auction> findByTradingDayOrderByStartTime (TradingDay day);
	
	Auction findByLot (Lot lot);
}
