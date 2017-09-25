package by.intexsoft.auction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table (name = "authorities")
public class Authority extends AbstractEntity implements GrantedAuthority {

	private static final long serialVersionUID = 2414844874810009406L;
	
	@Column
    public String authority;

    public Authority(String authority) {
		this.authority = authority;
	}
    
	@Override
	public String getAuthority() {
		return this.authority;
	}

	public Authority() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Authority [" + authority + "]";
	}
}
