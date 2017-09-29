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
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuctionService;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.TradingDayService;

@RestController
@RequestMapping("auction")
public class AuctionController {
	
	private AuctionService auctionService;
	private TradingDayService tradingDayService;
	private AuthenticationService authenticationService;
	
	@Autowired
	public AuctionController(AuctionService auctionService, TradingDayService tradingDayService,
			AuthenticationService authenticationService) {
		this.auctionService = auctionService;
		this.tradingDayService = tradingDayService;
		this.authenticationService = authenticationService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam (value = "date", required = false) String date) {
		if (date == null) return new ResponseEntity<> (auctionService.findAll(), HttpStatus.OK);
		TradingDay currentDay = tradingDayService.getByTradingDate(date);
		return new ResponseEntity<>(auctionService.getForDay(currentDay), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestParam (value = "queue", required = true) Boolean isQueue, @RequestBody Auction auction) {
		String status = "onsale";
		if (isQueue) status = "queue";
		return new ResponseEntity<>(auctionService.save(auction, status), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get (@PathVariable(value = "id") int auctionId) {
		auctionService.validIsNotExpired(auctionId);
		return new ResponseEntity<> (auctionService.find(auctionId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/price", method = RequestMethod.GET)
	public ResponseEntity<?> getCurrentBid (@PathVariable(value = "id") int auctionId) {
		auctionService.validIsNotExpired(auctionId);
		return new ResponseEntity<> (auctionService.find(auctionId).currentBid, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/bid", method = RequestMethod.GET)
	public ResponseEntity<?> placeBid (@PathVariable(value = "id") int auctionId) {
		auctionService.validIsNotExpired(auctionId);
		User user = authenticationService.getUser();
		return new ResponseEntity<> (auctionService.placeBid(auctionId, user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete (@PathVariable(value = "id") int auctionId) {
		auctionService.delete(auctionId);
		return new ResponseEntity<> (HttpStatus.OK);
	}
}
