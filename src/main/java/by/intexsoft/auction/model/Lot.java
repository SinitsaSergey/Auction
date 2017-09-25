package by.intexsoft.auction.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "lots")
public class Lot extends AbstractEntity{
	
	private static final long serialVersionUID = -5711393423535091909L;
	
	@Column (nullable = false, length = 255)
	public String title;
	
	@Column 
	public String description;

	@Column(name = "start_price", scale = 2)
	public BigDecimal startPrice;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "seller_id")
	public User seller;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "status_id")
	public Status status; // registered / on sale / queue / saled / confirmed
	
	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "auction_id")
	public Auction auction;
	
	@Column
	public Date added;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lot [title=" + title + ", description=" + description + ", startPrice=" + startPrice + ", seller="
				+ seller + ", status=" + status + "]";
	}
}
