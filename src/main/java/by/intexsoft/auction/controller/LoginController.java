package by.intexsoft.auction.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.UserService;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("login")
public class LoginController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoginController.class);
	
	private final UserService userService;
	private final AuthenticationService authenticationService;
	
	@Autowired
	public LoginController(UserService userService, AuthenticationService authenticationService) {
		this.userService = userService;
		this.authenticationService = authenticationService;
	}
	
	
	/**
	 * ”правл€ет входом пользовател€ в систему по username и password
	 * @param requestUser содержащий username и password
	 * @return токен дл€ аутентификации
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> login (@RequestBody User requestUser){
		LOGGER.info("start login by user");
		if (requestUser.username==null||requestUser.password==null) return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		User user = userService.getUserByUsername(requestUser.username);
		String token = authenticationService.generateToken(user, requestUser.password);
		if (token == null) return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		HttpHeaders authHeader = new HttpHeaders();
		authHeader.set("Authorization", token);
		return new ResponseEntity<>(true, authHeader, HttpStatus.OK);
	}

}
