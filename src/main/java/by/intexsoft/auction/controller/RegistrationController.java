package by.intexsoft.auction.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.Authority;
import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.AuthorityService;
import by.intexsoft.auction.service.UserService;

@RestController
@RequestMapping("registration")
public class RegistrationController {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService, AuthorityService authorityService,
			AuthenticationService authenticationService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		// LOGGER.info("Start registration user");
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}

}