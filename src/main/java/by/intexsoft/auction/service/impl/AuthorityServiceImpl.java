package by.intexsoft.auction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Authority;
import by.intexsoft.auction.repository.AuthorityRepository;
import by.intexsoft.auction.service.AuthorityService;

@Service
public class AuthorityServiceImpl extends AbstractServiceEntityImpl<Authority> implements AuthorityService {

	@Autowired
	private AuthorityRepository repository;
	
	@Override
	public Authority findByAuthority(String authority) {
		System.out.println(repository);
		return repository.findByAuthority(authority);
	}
	
	

}
