package by.intexsoft.auction.model;

import static javax.persistence.FetchType.EAGER;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "auctions")
public class Auction extends AbstractEntity {

	private static final long serialVersionUID = 2493466916836151381L;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "day_id")
	public TradingDay tradingDay;
	
	@Column(name = "start_time")
	public Date startTime;
	
	@Column(name = "finish_time")
	public Date finishTime;
	
	@Column(name = "step_price", scale = 2)
	public BigDecimal stepPrice;
	
	@Column (name = "current_bid", scale =2)
	public BigDecimal currentBid;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "bidholder_id")
	public User bidholder;
	
	@Column (name = "bid_time")
	public Date bidTime;

	@OneToMany(mappedBy = "auction", fetch = EAGER)
	public Set<Bid> bidList;
	
	@OneToOne(fetch = EAGER)
	@JoinColumn(name = "lot_id")
	public Lot lot;
	
	public Auction() {
	}
	
	public Auction (Lot lot) {
		this.lot = lot;
	}
}
