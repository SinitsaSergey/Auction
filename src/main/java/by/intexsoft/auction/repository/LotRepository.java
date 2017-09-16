package by.intexsoft.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Lot;

public interface LotRepository extends JpaRepository<Lot, Integer> {

}
