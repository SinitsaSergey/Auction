package by.intexsoft.auction.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "trading_day")
public class TradingDay extends AbstractEntity{

	private static final long serialVersionUID = 4112777822259677565L;

	@Column (name = "trading_date", nullable = false)
	public Calendar tradingDate;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "manager_id")
	public User manager;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tradingDay", fetch = LAZY)
	public Set<Auction> auctions;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TradingDay [tradingDate=" + tradingDate + ", manager=" + manager + "]";
	}

}
