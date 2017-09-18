package by.intexsoft.auction.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.TradingDay;

public interface TradingDayRepository extends JpaRepository<TradingDay, Integer> {
	
	TradingDay findByTradingDate (Calendar tradingDate);

}
