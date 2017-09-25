package by.intexsoft.auction.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.Status;
import by.intexsoft.auction.repository.LotRepository;
import by.intexsoft.auction.service.LotService;
import by.intexsoft.auction.service.StatusService;

@Service
public class LotServiceImpl extends AbstractServiceEntityImpl<Lot> implements LotService {

	@Autowired
	private LotRepository repository;

	@Autowired
	StatusService statusService;

	@Override
	public Lot save(Lot lot) {
		lot.added = new Date();
		return repository.save(lot);
	}

	@Override
	public List<Lot> getByStatus(Status status) {
		return repository.findByStatus(status);
	}

	@Override
	public List<Lot> getFreeLots() {
		List<Lot> regLots = repository.findByStatus(statusService.getByStatus("registered"));
		List<Lot> queueLots = repository.findByStatus(statusService.getByStatus("queue"));
		List <Lot> result = new ArrayList<Lot>(regLots);
		result.addAll(queueLots);
		return result;
	}

}
