package by.intexsoft.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
	
	Status findByStatus (String status);
}
