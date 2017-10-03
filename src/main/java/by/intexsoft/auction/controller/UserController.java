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
@RequestMapping("user")
public class UserController {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(UserController.class);

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
		if (username == null) return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody User user) {
		// LOGGER.info("Start update user");
		if (!user.username.equals(authenticationService.getUser().username)) {
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public ResponseEntity<?> changePassword(@RequestBody String password) {
		User changedUser = userService.changePassword (authenticationService.getUser().username, password);
		return new ResponseEntity<>(changedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.PUT)
	public ResponseEntity<?> setRole(@RequestParam (value = "authority", required = true) String authority, @RequestBody User user) {
		return new ResponseEntity<>(userService.changeRole(user, authority), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ban", method = RequestMethod.PUT)
	public ResponseEntity<?> setBan(@RequestParam (value = "blocked", required = true) boolean blocked, @RequestBody User user) {
		User updatingUser = userService.find(user.getId());
		updatingUser.isBanned = blocked;
		return new ResponseEntity<>(userService.save(updatingUser), HttpStatus.OK);
	}
	
}