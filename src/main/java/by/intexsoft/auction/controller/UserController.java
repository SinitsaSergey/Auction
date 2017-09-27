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
import by.intexsoft.auction.service.AuthorityService;
import by.intexsoft.auction.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	private AuthorityService authorityService;

	@Autowired
	public UserController(UserService userService, AuthorityService authorityService) {
		this.userService = userService;
		this.authorityService = authorityService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getByParams(@RequestParam (value = "username", required = false) String username) {
		if (username == null) return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		// LOGGER.info("Start registration user");
		user.registrated = new Date();
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authorityService.findByAuthority("ROLE_USER"));
		user.authorities = authorities;
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody User user) {
		// LOGGER.info("Start update user");
		int userId = user.getId();
		
		
		
		user.registrated = new Date();
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authorityService.findByAuthority("ROLE_USER"));
		user.authorities = authorities;
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.PUT)
	public ResponseEntity<?> setRole(@RequestParam (value = "authority", required = true) String authority, @RequestBody User user) {
		User updatingUser = userService.find(user.getId());
		Set <Authority> authorities = new HashSet<>();
		authorities.add(authorityService.findByAuthority("ROLE_"+authority.toUpperCase()));
		updatingUser.authorities = authorities;
		return new ResponseEntity<>(userService.save(updatingUser), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ban", method = RequestMethod.PUT)
	public ResponseEntity<?> setBan(@RequestParam (value = "blocked", required = true) boolean blocked, @RequestBody User user) {
		User updatingUser = userService.find(user.getId());
		updatingUser.isBanned = blocked;
		return new ResponseEntity<>(userService.save(updatingUser), HttpStatus.OK);
	}
	
}