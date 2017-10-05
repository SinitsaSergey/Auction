package by.intexsoft.auction.controller;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
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
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.TradingDayService;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("trading-day")
public class TradingDayController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TradingDayController.class);

	private TradingDayService tradingDayService;
	private AuthenticationService authenticationService;

	@Autowired
	public TradingDayController(TradingDayService tradingDayService, AuthenticationService authenticationService) {
		this.tradingDayService = tradingDayService;
		this.authenticationService = authenticationService;
	}
	
	@RequestMapping(value = "/{date}", method = RequestMethod.POST)
	public ResponseEntity<?> insert(@PathVariable(value = "date") String date, @RequestBody User manager) {
		LOGGER.info("insert trading day");
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
		LOGGER.info("get trading day by date");
		return new ResponseEntity<>(tradingDayService.getByTradingDate(date), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public ResponseEntity<?> getMyDays(){
		LOGGER.info("get days by manager");
		User manager = authenticationService.getUser();
		return new ResponseEntity<>(tradingDayService.getByManager(manager), HttpStatus.OK);
	}

}
