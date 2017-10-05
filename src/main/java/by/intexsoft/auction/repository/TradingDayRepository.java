package by.intexsoft.auction.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;

public interface TradingDayRepository extends JpaRepository<TradingDay, Integer> {
	
	TradingDay findByTradingDate (Calendar tradingDate);
	
	List <TradingDay> findByManager (User manager);
}
