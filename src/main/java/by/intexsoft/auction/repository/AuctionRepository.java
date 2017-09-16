package by.intexsoft.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {
	
}
