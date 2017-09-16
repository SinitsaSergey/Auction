package by.intexsoft.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	
	Authority findByAuthority(String authority);

}