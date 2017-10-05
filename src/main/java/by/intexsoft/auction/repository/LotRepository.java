package by.intexsoft.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.Status;
import by.intexsoft.auction.model.User;

public interface LotRepository extends JpaRepository<Lot, Integer> {
	
	List<Lot> findByStatus (Status status);
	
	List<Lot> findBySeller (User seller);
}
