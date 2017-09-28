package by.intexsoft.auction.service;

import by.intexsoft.auction.model.User;

public interface UserService extends AbstractEntityService<User> {
	
	User getUserByUsername (String username);

	User changePassword(String username, String password);
	
	User update(User user);

	User changeRole(User user, String authority);
	
	User changeBan(User user, boolean blocked);

}
