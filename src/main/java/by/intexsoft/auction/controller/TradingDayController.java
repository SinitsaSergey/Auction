package by.intexsoft.auction.controller;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.TradingDayService;

@RestController
@RequestMapping("trading-day")
public class TradingDayController {

	private TradingDayService tradingDayService;

	@Autowired
	public TradingDayController(TradingDayService tradingDayService) {
		this.tradingDayService = tradingDayService;
	}

	@RequestMapping(value = "/{date}", method = RequestMethod.POST)
	public ResponseEntity<?> insert(@PathVariable(value = "date") String date, @RequestBody User manager) {
		TradingDay tradingDay = new TradingDay();
		tradingDay.manager = manager;
		List<Integer> dateParams = Arrays.asList(date.split("-")).stream()
				.map(s -> Integer.parseInt(s))
				.collect(Collectors.toList());
		tradingDay.tradingDate = new GregorianCalendar(dateParams.get(0), dateParams.get(1)-1, dateParams.get(2));
		return new ResponseEntity<>(tradingDayService.save(tradingDay), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{date}", method = RequestMethod.GET)
	public ResponseEntity<?> getByDate(@PathVariable(value = "date") String date) {
		return new ResponseEntity<>(tradingDayService.getByTradingDate(date), HttpStatus.OK);
	}

}
