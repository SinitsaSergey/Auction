package by.intexsoft.auction.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Authority;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.repository.UserRepository;
import by.intexsoft.auction.service.AuthorityService;
import by.intexsoft.auction.service.UserService;

@Service
public class UserServiceImpl extends AbstractServiceEntityImpl<User> implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private AuthorityService authorityService;
	
	@Override
	public User getUserByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public User registration(User user) {
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authorityService.findByAuthority("USER_ROLE"));
		user.authorities = authorities;
		user.registrated = new Date();
		return repository.save(user);
	}

}
