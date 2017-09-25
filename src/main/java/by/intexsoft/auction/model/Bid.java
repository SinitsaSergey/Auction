package by.intexsoft.auction.model;

import static javax.persistence.FetchType.LAZY;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "bids")
public class Bid extends AbstractEntity {
	
	private static final long serialVersionUID = 3137482467791075835L;

	@Column(scale = 2)
	public BigDecimal value;
	
	@OneToOne
    @JoinColumn(name = "bidder_id")
	public User bidder;
	
	@Column (name = "bid_time")
	public Timestamp bidTime;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "auction_id")
	public Auction auction;
}
