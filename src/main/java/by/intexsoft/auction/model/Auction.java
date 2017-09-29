package by.intexsoft.auction.model;

import static javax.persistence.FetchType.EAGER;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "auctions")
public class Auction extends AbstractEntity {

	private static final long serialVersionUID = 2493466916836151381L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "day_id")
	public TradingDay tradingDay;
	
	@Column(name = "start_time")
	public Calendar startTime;
	
	@Column(name = "finish_time")
	public Calendar finishTime;
	
	@Column(name = "step_price", scale = 2)
	public BigDecimal stepPrice;
	
	@Column (name = "current_bid", scale =2)
	public BigDecimal currentBid;
	
	@Column (name = "bid_time")
	public Timestamp bidTime;

	@OneToMany(mappedBy = "auction", fetch = FetchType.EAGER)
	public Set<Bid> bidList;
	
	@OneToOne(fetch = EAGER)
	@JoinColumn(name = "lot_id")
	public Lot lot;
	
	public Auction() {
	}
	
	public Auction (Lot lot) {
		this.lot = lot;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Auction [tradingDay=" + tradingDay.tradingDate + ", startTime=" + startTime + ", finishTime=" + finishTime
				+ ", stepPrice=" + stepPrice + ", bidList=" + bidList + ", lot=" + lot.title + "]";
	}

}
