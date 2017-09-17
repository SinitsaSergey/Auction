package by.intexsoft.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.service.LotService;

@RestController
@RequestMapping("lot")
public class LotController {
	
	private LotService lotService;
	
	@Autowired	
	public LotController(LotService lotService) {
		this.lotService = lotService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll (){
		List<Lot> lots = lotService.findAll();
		return new ResponseEntity<>(lots, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Lot lot) {
		lotService.save(lot);
		return new ResponseEntity<>(lot, HttpStatus.CREATED);
	}
	
}
