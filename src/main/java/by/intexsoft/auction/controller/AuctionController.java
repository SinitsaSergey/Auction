package by.intexsoft.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.service.AuctionService;

@RestController
@RequestMapping("auction")
public class AuctionController {
	
	private AuctionService auctionService;

	@Autowired
	public AuctionController(AuctionService auctionService) {
		this.auctionService = auctionService;
	}
	

}
