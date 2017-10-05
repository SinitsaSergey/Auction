package by.intexsoft.auction.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
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
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("lot")
public class LotController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LotController.class);

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
		LOGGER.info("get lot by id");
		return new ResponseEntity<> (lotService.find(lotId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete (@PathVariable(value = "id") int lotId) {
		LOGGER.info("delete lot by id");
		lotService.delete(lotId);
		return new ResponseEntity<> (true, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "status", required = true) String status) {
		LOGGER.info("get all lots by status");
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
		LOGGER.info("insert lot");
		lot.seller = authenticationService.getUser();
		return new ResponseEntity<>(lotService.save(lot, "registered"), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ResponseEntity<?> confirm (@RequestBody Lot lot) {
		LOGGER.info("confirm lot");
		return new ResponseEntity<>(lotService.save(lot, "confirmed"), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public ResponseEntity<?> getByCurrentUser() {
		LOGGER.info("get lots by seller");
		User currentUser = authenticationService.getUser();
		return new ResponseEntity<> (lotService.getByUser(currentUser), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/purchased", method = RequestMethod.GET)
	public ResponseEntity<?> getPurchasedByCurrentUser() {
		LOGGER.info("get purchased lots");
		User currentUser = authenticationService.getUser();
		return new ResponseEntity<> (lotService.getPurchasedByUser(currentUser), HttpStatus.OK);
	}

}
