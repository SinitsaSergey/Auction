package by.intexsoft.auction.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.repository.LotRepository;
import by.intexsoft.auction.service.LotService;

@Service
public class LotServiceImpl extends AbstractServiceEntityImpl<Lot> implements LotService {
	
	@Autowired
	private LotRepository repository;
	
	@Override
	public Lot save (Lot lot) {
		lot.added = new Date();
		return repository.save(lot);
	}

}
