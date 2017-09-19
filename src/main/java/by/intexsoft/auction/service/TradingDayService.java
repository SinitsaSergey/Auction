package by.intexsoft.auction.service;

import java.util.Date;

import by.intexsoft.auction.model.TradingDay;

public interface TradingDayService extends AbstractEntityService<TradingDay> {
	
	TradingDay getByTradingDate (String stringDate);

}
