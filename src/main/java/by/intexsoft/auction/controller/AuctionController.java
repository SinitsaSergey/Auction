package by.intexsoft.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.service.AuctionService;
import by.intexsoft.auction.service.TradingDayService;

@RestController
@RequestMapping("auction")
public class AuctionController {
	
	private AuctionService auctionService;
	private TradingDayService tradingDayService;
	
	@Autowired
	public AuctionController(AuctionService auctionService, TradingDayService tradingDayService) {
		super();
		this.auctionService = auctionService;
		this.tradingDayService = tradingDayService;
	}	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam (value = "date", required = false) String date) {
		if (date == null) return new ResponseEntity<> (auctionService.findAll(), HttpStatus.OK);
		TradingDay currentDay = tradingDayService.getByTradingDate(date);
		return new ResponseEntity<>(auctionService.getForDay(currentDay), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Auction auction) {
		auctionService.save(auction);
		return new ResponseEntity<>(auction, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get (@PathVariable(value = "id") int auctionId) {
		return new ResponseEntity<> (auctionService.find(auctionId), HttpStatus.OK);
	}
}
