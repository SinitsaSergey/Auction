package by.intexsoft.auction.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.repository.TradingDayRepository;
import by.intexsoft.auction.service.TradingDayService;

@Service
public class TradingDayServiceImpl extends AbstractServiceEntityImpl<TradingDay> implements TradingDayService {

	@Autowired
	private TradingDayRepository repository;

	/* 
	 * yyyy-mm-dd
	 */
	@Override
	public TradingDay getByTradingDate(String stringDate) {
		List<Integer> dateParams = Arrays.asList(stringDate.split("-")).stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
		return repository.findByTradingDate(new GregorianCalendar(dateParams.get(0), dateParams.get(1), dateParams.get(2)));
	}

}
