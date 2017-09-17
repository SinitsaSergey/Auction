package by.intexsoft.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.UserService;

@RestController
public class UserController {

	//private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user) {
		//LOGGER.info("Start registration user");
		try {
			return new ResponseEntity<>(userService.registration(user), HttpStatus.CREATED);
		} catch (Exception e) {
			//LOGGER.info("Error in aregistration. " + e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}