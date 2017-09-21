package by.intexsoft.auction.model.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import by.intexsoft.auction.model.Authority;
import by.intexsoft.auction.model.User;

public class UserDTO extends User implements Serializable{

	private static final long serialVersionUID = 7743895234592446630L;
	
	public int id;
	
	public String username;
	
	public String password;

	public String firstName;

	public String lastName;

	public String email;
	
	public String phone;

	public Date registrated;
	
	public Set<Authority> authorities;
	
	public boolean isBanned;
	
	public UserDTO (User user){
		this.id = user.getId();
		this.username = user.username;
		this.password = "[isHidden]";
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.phone = user.phone;
		this.registrated = user.registrated;
		this.authorities = user.authorities;
		this.isBanned = user.isBanned;
	}

	public User toUser() {
		Set <Authority> authorities = this.authorities;
		User user = this;
		user.authorities = authorities;
		return user;
	}

}
