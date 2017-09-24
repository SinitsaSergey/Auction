package by.intexsoft.auction.service;

import java.util.List;

import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;

public interface TradingDayService extends AbstractEntityService<TradingDay> {
	
	TradingDay getByTradingDate(String dateString);
	
	List <TradingDay> getByManager (User manager);
	
}
