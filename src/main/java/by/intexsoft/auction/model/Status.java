package by.intexsoft.auction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Status extends AbstractEntity {

	private static final long serialVersionUID = -9164967956296811127L;
	
	@Column
    public String status;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Status [status=" + status + "]";
	}
}
