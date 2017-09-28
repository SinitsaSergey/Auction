package by.intexsoft.auction;

import static java.util.Calendar.OCTOBER;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
		lotStatus1.status = "onsale";
		statusService.save(lotStatus1);
		Status lotStatus2 = new Status();
		lotStatus2.status = "queue";
		statusService.save(lotStatus2);
		Status lotStatus3 = new Status();
		lotStatus3.status = "saled";
		statusService.save(lotStatus3);
		Status lotStatus4 = new Status();
		lotStatus4.status = "confirmed";
		statusService.save(lotStatus4);

		User user1 = new User();
		user1.username = "username";
		user1.password = "password";
		user1.firstName = "Имя";
		user1.lastName = "Фамилия";
		user1.email = "good@mail.com";
		user1.phone = "+375000000000";
		user1.registrated = new Date();
		Set<Authority> authorities = new HashSet<>();
		authorities.add(authorityService.findByAuthority("ROLE_USER"));
		user1.authorities = authorities;
		userService.save(user1);
		
		User user2 = new User();
		user2.username = "username2";
		user2.password = "password";
		user2.firstName = "Имя2";
		user2.lastName = "Фамилия2";
		user2.email = "good2@mail.com";
		user2.phone = "+375000000002";
		user2.registrated = new Date();
		Set<Authority> authorities2 = new HashSet<>();
		authorities2.add(authorityService.findByAuthority("ROLE_USER"));
		user2.authorities = authorities2;
		userService.save(user2);

		User admin = new User();
		admin.username = "admin";
		admin.password = "admin";
		admin.firstName = "Админ";
		admin.lastName = "Админский";
		admin.email = "admin@mail.com";
		admin.phone = "+375999999999";
		admin.registrated = new Date();
		Set<Authority> authorities1 = new HashSet<>();
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
		authorities11.add(authorityService.findByAuthority("ROLE_MANAGER"));
		manager.authorities = authorities11;
		userService.save(manager);
		
		Lot lot1 = new Lot();
		lot1.title = "Какой-то лот";
		lot1.description = "Продам что-нибудь за деньги";
		lot1.startPrice = new BigDecimal(13).setScale(2, RoundingMode.HALF_EVEN);
		lot1.seller = user2;
		lotService.save(lot1, "registered");
		
		Lot lot2 = new Lot();
		lot2.title = "Какой-то лот2";
		lot2.description = "Продам что-нибудь за деньги";
		lot2.startPrice = new BigDecimal(15).setScale(2, RoundingMode.HALF_EVEN);
		lot2.seller = user1;
		lotService.save(lot2, "registered");

		TradingDay tradingDay = new TradingDay();
		tradingDay.tradingDate = new GregorianCalendar(2017, OCTOBER, 2);
		tradingDay.manager = manager;
		dayService.save(tradingDay);

		Auction auction1 = new Auction(lot2);
		auction1.tradingDay = tradingDay;
		Calendar calendar = new GregorianCalendar(2017, Calendar.OCTOBER, 2, 9, 0);
		auction1.startTime = calendar;
		auction1.stepPrice = new BigDecimal(1).setScale(2, RoundingMode.HALF_EVEN);
		auctionService.save(auction1, "onsale");
		
		TradingDay tdDay = dayService.getByTradingDate("2017-10-02");
		
		
		System.out.println(auction1);

		/*System.out.println(auctionService.getForDay(tdDay));
		
		System.out.println(dayService.getByManager(manager));*/
		

		context.close();
	}
}
