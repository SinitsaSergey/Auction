package by.intexsoft.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.service.BidService;

@RestController
@RequestMapping("bid")
public class BidController {
	
	private BidService bidService;

	@Autowired
	public BidController(BidService bidService) {
		this.bidService = bidService;
	}
	
	

}
