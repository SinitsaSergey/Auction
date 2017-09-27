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

	@Autowired
	private AuthorityService authorityService;

	@Override
	public User getUserByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		user.password = passwordEncoder.encode(user.password);
		user.authorities = new HashSet<>();
		user.authorities.add(authorityService.findByAuthority("ROLE_USER"));
		user.email = user.email.toLowerCase();
		return repository.save(user);
	}

	@Override
	public User changePassword(String username, String password) {
		User user = repository.findByUsername(username);
		user.password = passwordEncoder.encode(password);
		return repository.save(user);
	}

	@Override
	public User update(User user) {
		User updatedUser = repository.findOne(user.getId());
		updatedUser.username = user.username;
		updatedUser.firstName = user.firstName;
		updatedUser.lastName = user.lastName;
		updatedUser.email = user.email;
		updatedUser.phone = user.phone;
		return repository.save(updatedUser);
	}

	/*
	 * @Override public List<User> findAll () { List <User> users =
	 * repository.findAll(); users.stream() .map(user -> user.password = null)
	 * .collect(Collectors.toList()); return users; }
	 */

}
