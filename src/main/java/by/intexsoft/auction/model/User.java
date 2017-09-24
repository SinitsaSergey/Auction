package by.intexsoft.auction.model;

import static javax.persistence.FetchType.EAGER;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User extends AbstractEntity{
	
	private static final long serialVersionUID = 4507851224901060824L;

	@Column(unique = true, nullable = false, length = 30)
	public String username;
	
	@Column(nullable = false, length = 30)
	public String password;

	@Column(name = "first_name", nullable = false, length = 50)
	public String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	public String lastName;

	@Column(unique = true, nullable = false, length = 50)
	public String email;
	
	@Column (nullable = false, length = 20)
	public String phone;

	@Column(nullable = false)
	public Date registrated;
	
	@ManyToMany(fetch = EAGER)
	@JoinTable(name = "users_authorities",
	  joinColumns = @JoinColumn(name = "user_id"),
	  inverseJoinColumns = @JoinColumn(name = "authority_id"))
	public Set<Authority> authorities;
	
	@Column
	public boolean isBanned;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone + ", registrated=" + registrated + ", authorities="
				+ authorities + ", isBlocked=" + isBanned + "]";
	}
}
