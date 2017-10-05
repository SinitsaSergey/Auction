package by.intexsoft.auction.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.AuthorityService;
import by.intexsoft.auction.service.UserService;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("user")
public class UserController {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	private AuthenticationService authenticationService;
	
	
	@Autowired
	public UserController(UserService userService, AuthorityService authorityService,
			AuthenticationService authenticationService) {
		this.userService = userService;
		this.authenticationService = authenticationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getByParams(@RequestParam (value = "username", required = false) String username) {
		LOGGER.info("get user by params");
		if (username == null) return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody User user) {
		LOGGER.info("update user");
		if (!user.username.equals(authenticationService.getUser().username)) {
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public ResponseEntity<?> changePassword(@RequestBody String password) {
		LOGGER.info("change user password");
		User changedUser = userService.changePassword (authenticationService.getUser().username, password);
		return new ResponseEntity<>(changedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.PUT)
	public ResponseEntity<?> setRole(@RequestParam (value = "authority", required = true) String authority, @RequestBody User user) {
		LOGGER.info("change user role");
		return new ResponseEntity<>(userService.changeRole(user, authority), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ban", method = RequestMethod.PUT)
	public ResponseEntity<?> setBan(@RequestParam (value = "blocked", required = true) boolean blocked, @RequestBody User user) {
		LOGGER.info("block user");
		User updatingUser = userService.find(user.getId());
		updatingUser.isBanned = blocked;
		return new ResponseEntity<>(userService.save(updatingUser), HttpStatus.OK);
	}
	
}