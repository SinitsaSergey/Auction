package by.intexsoft.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.service.TradingDayService;

@RestController
@RequestMapping("trading-day")
public class TradingDayController {

	private TradingDayService tradingDayService;

	@Autowired
	public TradingDayController(TradingDayService tradingDayService) {
		this.tradingDayService = tradingDayService;
	}

}
