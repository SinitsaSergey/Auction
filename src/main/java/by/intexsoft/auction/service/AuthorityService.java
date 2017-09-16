package by.intexsoft.auction.service;

import by.intexsoft.auction.model.Authority;

public interface AuthorityService extends AbstractEntityService<Authority> {

	Authority findByAuthority(String authority);
}
