package by.intexsoft.auction.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.repository.TradingDayRepository;
import by.intexsoft.auction.service.TradingDayService;

@Service
public class TradingDayServiceImpl extends AbstractServiceEntityImpl<TradingDay> implements TradingDayService {

	@Autowired
	private TradingDayRepository repository;

	@Override
	public TradingDay getByTradingDate(String dateString) {
		Calendar tradingDate = convertToCalendar(dateString);
		return repository.findByTradingDate(tradingDate);
	}
	
	private Calendar convertToCalendar (String dateString) {
		List<Integer> dateParams = Arrays.asList(dateString.split("-")).stream()
				 .map(s -> Integer.parseInt(s))
	                .collect(Collectors.toList());
		return new GregorianCalendar(dateParams.get(0), dateParams.get(1)-1, dateParams.get(2));
	}

	@Override
	public List<TradingDay> getByManager(User manager) {
		return repository.findByManager(manager);
	}
}
