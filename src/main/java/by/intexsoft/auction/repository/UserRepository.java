package by.intexsoft.auction.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.auction.model.Authority;
import by.intexsoft.auction.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername (String username);
	
}
