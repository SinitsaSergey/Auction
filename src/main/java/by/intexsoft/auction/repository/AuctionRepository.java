package by.intexsoft.auction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {
	
	List<Auction> findByTradingDayOrderByStartTime (TradingDay day);
	
	List<Auction> findByTradingDayAndStartTimeAfterOrderByStartTime (TradingDay tradingDay, Date startTime);
	
	Auction findByLot (Lot lot);
	
	List<Auction> findByTradingDayAndStartTimeIsNull (TradingDay tradingDay);
	
	List<Auction> findByStartTimeBetween (Date start, Date end);
	
	List<Auction> findByFinishTimeBetween (Date start, Date end);
	
	List<Auction> findByBidholder (User bidholder);
	
}
