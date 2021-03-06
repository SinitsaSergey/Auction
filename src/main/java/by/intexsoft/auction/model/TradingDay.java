package by.intexsoft.auction.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name = "trading_days")
public class TradingDay extends AbstractEntity{

	private static final long serialVersionUID = 4112777822259677565L;

	@Column (name = "trading_date", nullable = false)
	public Calendar tradingDate;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "manager_id")
	public User manager;
	
	@JsonBackReference
	@OneToMany(mappedBy = "tradingDay", fetch = LAZY)
	public Set<Auction> auctions;

}
