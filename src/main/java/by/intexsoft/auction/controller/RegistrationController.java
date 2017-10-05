package by.intexsoft.auction.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.AuthorityService;
import by.intexsoft.auction.service.UserService;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("registration")
public class RegistrationController {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(RegistrationController.class);

	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService, AuthorityService authorityService,
			AuthenticationService authenticationService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		LOGGER.info("Start registration user");
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}

}