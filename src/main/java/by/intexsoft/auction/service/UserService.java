package by.intexsoft.auction.service;

import by.intexsoft.auction.model.User;

public interface UserService extends AbstractEntityService<User> {
	
	User getUserByUsername (String username);

	User registration(User user);

}
