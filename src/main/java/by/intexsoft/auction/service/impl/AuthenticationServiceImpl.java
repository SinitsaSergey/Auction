package by.intexsoft.auction.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import by.intexsoft.auction.model.User;
import by.intexsoft.auction.service.AuthenticationService;
import by.intexsoft.auction.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final int EXPIRATION_HOURS = 24;
	private static final String KEY_WORD = "CHUCKNORRIS";
	private static final String JWT_PREFIX = "Bearer";
	private static final String HEADER = "Authorization";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private User user;

	/*@Override
	public String generateToken(HttpServletResponse response, Authentication authentication) {
		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		Claims claims = Jwts.claims().setSubject(authentication.getName());
		claims.put("scopes", authorities);
		String JWT = Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_HOURS))
				.signWith(SignatureAlgorithm.HS256, KEY_WORD)

				.compact();
		response.addHeader(HEADER, JWT_PREFIX + " " + JWT);
		return JWT_PREFIX + " " + JWT;
	}*/

	@Override
	public String generateToken(User user, String password) {
		if (user == null || password == null)
			return null;
		if (!passwordEncoder.matches(password, user.password)) return null;
		Map<String, Object> tokenData = new HashMap<>();
		tokenData.put("scopes", AuthorityUtils.authorityListToSet(user.authorities));
		tokenData.put("username", user.username);
		tokenData.put("password", user.password);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, EXPIRATION_HOURS);
		tokenData.put("expiration_time", calendar.getTime());
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setExpiration(calendar.getTime());
		jwtBuilder.setClaims(tokenData);
		return JWT_PREFIX + " " +jwtBuilder.signWith(SignatureAlgorithm.HS512, KEY_WORD).compact();
	}

	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER);
		if (token == null) return null;
		token = token.replaceAll(JWT_PREFIX, "");
		Jws<Claims> tokenData = Jwts.parser().setSigningKey(KEY_WORD).parseClaimsJws(token);
		String username = tokenData.getBody().get("username").toString();
		this.user = userService.getUserByUsername(username);
		String password = tokenData.getBody().get("password").toString();
		@SuppressWarnings("unchecked")
		List authorities = tokenData.getBody().get("scopes", List.class);
		//Date tokenExpiration = tokenData.getBody().get("expiration_time", Date.class);
		return new UsernamePasswordAuthenticationToken(username, password,
				AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities)));
	}

	@Override
	public Authentication getAuthentication(String token) {
		if (token == null) return null;
		token = token.replaceAll(JWT_PREFIX, "");
		Jws<Claims> tokenData = Jwts.parser().setSigningKey(KEY_WORD).parseClaimsJws(token);
		String username = tokenData.getBody().get("username").toString();
		String password = tokenData.getBody().get("password").toString();
		@SuppressWarnings("unchecked")
		List authorities = tokenData.getBody().get("scopes", List.class);
		//Date tokenExpiration = tokenData.getBody().get("expiration_time", Date.class);
		return new UsernamePasswordAuthenticationToken(username, password,
				AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities)));
	}

	@Override
	public User getUser() {
		return this.user;
	}

}
