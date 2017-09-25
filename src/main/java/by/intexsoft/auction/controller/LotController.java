package by.intexsoft.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.service.LotService;
import by.intexsoft.auction.service.StatusService;

@RestController
@RequestMapping("lot")
public class LotController {

	private LotService lotService;
	private StatusService statusService;

	@Autowired
	public LotController(LotService lotService, StatusService statusService) {
		this.lotService = lotService;
		this.statusService = statusService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "status", required = true) String status) {
		List<Lot> lots;
		if (status.equals("free")) {
			lots = lotService.getFreeLots();
		}else {
			lots = lotService.getByStatus(statusService.getByStatus(status));
		}
		return new ResponseEntity<>(lots, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Lot lot) {
		lotService.save(lot);
		return new ResponseEntity<>(lot, HttpStatus.CREATED);
	}

}
