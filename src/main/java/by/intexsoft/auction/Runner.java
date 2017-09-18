package by.intexsoft.auction;

import static java.util.Calendar.OCTOBER;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.intexsoft.auction.model.Auction;
import by.intexsoft.auction.model.Authority;
import by.intexsoft.auction.model.Lot;
import by.intexsoft.auction.model.Status;
import by.intexsoft.auction.model.TradingDay;
import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuctionService;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.AuthorityService;
import by.intexsoft.auction.service.BidService;
import by.intexsoft.auction.service.LotService;
import by.intexsoft.auction.service.StatusService;
import by.intexsoft.auction.service.TradingDayService;
import by.intexsoft.auction.service.UserService;

public class Runner {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		UserService userService = context.getBean(UserService.class);
		TradingDayService dayService = context.getBean(TradingDayService.class);
		AuctionService auctionService = context.getBean(AuctionService.class);
		LotService lotService = context.getBean(LotService.class);
		BidService bidService = context.getBean(BidService.class);
		AuthorityService authorityService = context.getBean(AuthorityService.class);
		StatusService statusService = context.getBean(StatusService.class);
		AuthenticationService authenticationService = context.getBean(AuthenticationService.class);

		Authority userRole = new Authority();
		userRole.authority = "ROLE_USER";
		authorityService.save(userRole);
		Authority managerRole = new Authority();
		managerRole.authority = "ROLE_MANAGER";
		authorityService.save(managerRole);
		Authority adminRole = new Authority();
		adminRole.authority = "ROLE_ADMIN";
		authorityService.save(adminRole);

		Status lotStatus = new Status();
		lotStatus.status = "registered";
		statusService.save(lotStatus);
		Status lotStatus1 = new Status();
		lotStatus1.status = "on sale";
		statusService.save(lotStatus1);

		User seller = new User();
		seller.username = "username";
		seller.password = "notpassword";
		seller.firstName = "Имя";
		seller.lastName = "Фамилия";
		seller.email = "good@mail.com";
		seller.phone = "+375000000000";
		seller.registrated = new Date();
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authorityService.findByAuthority("ROLE_USER"));
		seller.authorities = authorities;
		userService.save(seller);

		User admin = new User();
		admin.username = "admin";
		admin.password = "admin";
		admin.firstName = "Админ";
		admin.lastName = "Админский";
		admin.email = "admin@mail.com";
		admin.phone = "+375999999999";
		admin.registrated = new Date();
		Set<Authority> authorities1 = new HashSet<>();
		authorities1.add(authorityService.findByAuthority("ROLE_USER"));
		authorities1.add(authorityService.findByAuthority("ROLE_MANAGER"));
		authorities1.add(authorityService.findByAuthority("ROLE_ADMIN"));
		admin.authorities = authorities1;
		userService.save(admin);

		User manager = new User();
		manager.username = "manager";
		manager.password = "manpassword";
		manager.firstName = "Я";
		manager.lastName = "Менеджер";
		manager.email = "manager@mail.com";
		manager.phone = "+375000000090";
		manager.registrated = new Date();
		Set<Authority> authorities11 = new HashSet<>();
		authorities11.add(authorityService.findByAuthority("ROLE_USER"));
		authorities11.add(authorityService.findByAuthority("ROLE_MANAGER"));
		manager.authorities = authorities11;
		userService.save(manager);

		Lot lot1 = new Lot();
		lot1.title = "Какой-то лот";
		lot1.description = "Продам что-нибудь за деньги";
		lot1.startPrice = new BigDecimal(13).setScale(2, RoundingMode.HALF_EVEN);
		lot1.seller = seller;
		lot1.status = statusService.findByStatus("registered");
		lotService.save(lot1);

		TradingDay tradingDay = new TradingDay();
		tradingDay.tradingDate = new GregorianCalendar(2017, OCTOBER, 2);
		tradingDay.manager = manager;
		dayService.save(tradingDay);

		Auction auction1 = new Auction(lot1);
		auction1.tradingDay = tradingDay;
		auction1.startTime = new GregorianCalendar(2017, Calendar.OCTOBER, 2, 9, 0);
		auction1.duration = 120000;
		auction1.stepPrice = new BigDecimal(1).setScale(2, RoundingMode.HALF_EVEN);
		auctionService.save(auction1);

		System.out.println(auctionService.getForDay(tradingDay));

		context.close();
	}
}
