package by.intexsoft.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{

}
