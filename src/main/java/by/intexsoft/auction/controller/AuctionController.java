package by.intexsoft.auction.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuctionService;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.TradingDayService;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("auction")
public class AuctionController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoginController.class);
	
	private AuctionService auctionService;
	private TradingDayService tradingDayService;
	private AuthenticationService authenticationService;
	
	@Autowired
	public AuctionController(AuctionService auctionService, TradingDayService tradingDayService,
			AuthenticationService authenticationService) {
		this.auctionService = auctionService;
		this.tradingDayService = tradingDayService;
		this.authenticationService = authenticationService;
	}
	
	/**
	 * ��������� ���������� ������ ��������� �� ������� ����
	 * @param ���� ������
	 * @return ������ ��������� �� ������� ����
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam (value = "date", required = false) String date) {
		LOGGER.info("get all auctions by date");
		if (date == null) return new ResponseEntity<> (auctionService.findAll(), HttpStatus.OK);
		TradingDay currentDay = tradingDayService.getByTradingDate(date);
		return new ResponseEntity<>(auctionService.getForDay(currentDay), HttpStatus.OK);
	}
	
	/**
	 * ��������� ���������� ������ ��������� �� �������� "onsale"
	 * @param ���� ������
	 * @return ������ ��������� �� �������� "onsale"
	 */
	@RequestMapping(value = "/onsale", method = RequestMethod.GET)
	public ResponseEntity<?> getAllOnSale(@RequestParam (value = "date", required = true) String date) {
		LOGGER.info("get auctions on sale");
		TradingDay currentDay = tradingDayService.getByTradingDate(date);
		return new ResponseEntity<>(auctionService.getOnSaleForDay(currentDay), HttpStatus.OK);
	}

	/**
	 * ��������� ��������� ������ ��������
	 * @param isQueue true=������� � �������, false=������� � �������� ������
	 * @param auction
	 * @return ��������� ������� � ������� �������� ����
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestParam (value = "queue", required = true) Boolean isQueue, @RequestBody Auction auction) {
		LOGGER.info("get auctions in queue");
		if (!isQueue && auctionService.timeIsBusy(auction)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		String status = "onsale";
		if (isQueue) status = "queue";
		return new ResponseEntity<>(auctionService.save(auction, status), HttpStatus.CREATED);
	}
	
	/**
	 * ��������� ���������� �������� �� id
	 * @param auctionId
	 * @return ������� �� id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get (@PathVariable(value = "id") int auctionId) {
		LOGGER.info("get auction by id");
		auctionService.validIsNotExpired(auctionId);
		return new ResponseEntity<> (auctionService.find(auctionId), HttpStatus.OK);
	}
	
	/**
	 * ��������� ����������� ����������� ������� ������
	 * @param auctionId
	 * @return ������� ������
	 */
	@RequestMapping(value = "/{id}/price", method = RequestMethod.GET)
	public ResponseEntity<?> getCurrentBid (@PathVariable(value = "id") int auctionId) {
		LOGGER.info("get current price by auction id");
		auctionService.validIsNotExpired(auctionId);
		return new ResponseEntity<> (auctionService.find(auctionId).currentBid, HttpStatus.OK);
	}
	
	
	/**
	 * ��������� ���������� ����� ������
	 * @param id ��������
	 * @return ����������� ������
	 */
	@RequestMapping(value = "/{id}/bid", method = RequestMethod.GET)
	public ResponseEntity<?> placeBid (@PathVariable(value = "id") int auctionId) {
		LOGGER.info("place bid by auction id");
		auctionService.validIsNotExpired(auctionId);
		User user = authenticationService.getUser();
		return new ResponseEntity<> (auctionService.placeBid(auctionId, user), HttpStatus.OK);
	}
	
	
	/**
	 * ��������� ��������� �������� �� id
	 * @param auctionId
	 * @return true ���� ������� ������� ������
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete (@PathVariable(value = "id") int auctionId) {
		LOGGER.info("delete auction by id");
		auctionService.delete(auctionId);
		return new ResponseEntity<> (true, HttpStatus.OK);
	}
	
	/**
	 * ��������� ���������� �������� ������� ���������� ��������
	 * @param auctionId
	 * @return ����� ����������
	 */
	@RequestMapping(value = "/{id}/finish", method = RequestMethod.GET)
	public ResponseEntity<?> getFinishTime (@PathVariable(value = "id") int auctionId) {
		LOGGER.info("get auction finish time");
		return new ResponseEntity<> (auctionService.find(auctionId).finishTime, HttpStatus.OK);
	}
	
	/**
	 * ��������� ���������� �������� �� ����
	 * @param lotId
	 * @return ������� �� id ����
	 */
	@RequestMapping(value = "/lot/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByLot (@PathVariable(value = "id") int lotId) {
		LOGGER.info("get auction by lot id");
		return new ResponseEntity<> (auctionService.getByLot(lotId), HttpStatus.OK);
	}
	
}
