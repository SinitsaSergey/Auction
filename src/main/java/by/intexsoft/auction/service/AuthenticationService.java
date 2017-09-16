package by.intexsoft.auction.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import by.intexsoft.auction.model.User;

public interface AuthenticationService {

	public Authentication getAuthentication(HttpServletRequest request);
	
	public Authentication getAuthentication(String token);

	public String generateToken(User user, String password);

}
