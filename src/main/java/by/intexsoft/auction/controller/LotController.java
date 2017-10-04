package by.intexsoft.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.LotService;
import by.intexsoft.auction.service.StatusService;

@RestController
@RequestMapping("lot")
public class LotController {

	private LotService lotService;
	private StatusService statusService;
	private AuthenticationService authenticationService;

	@Autowired
	public LotController(LotService lotService, StatusService statusService,
			AuthenticationService authenticationService) {
		this.lotService = lotService;
		this.statusService = statusService;
		this.authenticationService = authenticationService;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get (@PathVariable(value = "id") int lotId) {
		return new ResponseEntity<> (lotService.find(lotId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete (@PathVariable(value = "id") int lotId) {
		lotService.delete(lotId);
		return new ResponseEntity<> (true, HttpStatus.OK);
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
		lot.seller = authenticationService.getUser();
		return new ResponseEntity<>(lotService.save(lot, "registered"), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public ResponseEntity<?> getByCurrentUser() {
		User currentUser = authenticationService.getUser();
		return new ResponseEntity<> (lotService.getByUser(currentUser), HttpStatus.OK);
	}

}
