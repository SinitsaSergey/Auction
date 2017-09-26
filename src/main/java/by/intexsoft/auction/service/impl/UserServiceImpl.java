package by.intexsoft.auction.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Authority;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.repository.UserRepository;
import by.intexsoft.auction.service.AuthorityService;
import by.intexsoft.auction.service.UserService;

@Service
public class UserServiceImpl extends AbstractServiceEntityImpl<User> implements UserService {

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public User getUserByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	@Override
	public User save (User user) {
		if (user.getId() == null) user.password = passwordEncoder.encode(user.password);
		return repository.save(user);
	}
	
	/*@Override
	public List<User> findAll () {
		List <User> users = repository.findAll();
		users.stream()
		.map(user -> user.password = null)
		.collect(Collectors.toList());
		return users;
	}*/
	
}
