package by.intexsoft.auction.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.Authority;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuthorityService;
import by.intexsoft.auction.service.UserService;

@RestController
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

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		// LOGGER.info("Start registration user");
		user.registrated = new Date();
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authorityService.findByAuthority("ROLE_USER"));
		user.authorities = authorities;
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}
}