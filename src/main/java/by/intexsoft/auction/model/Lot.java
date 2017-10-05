package by.intexsoft.auction.model;

import static javax.persistence.FetchType.EAGER;
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
	public Status status;
	
	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "auction_id")
	public Auction auction;
	
	@Column (name = "image_path")
	public String imagePath;
	
	@Column
	public Timestamp added;
}
