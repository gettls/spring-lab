package hello.jwttutorial.jwt;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {
	
	private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	
	private static final String AUTHORITIES_KEY = "auth";
	
	private final String secret;
	private final long tokenValidityInMillseconds;
	
	private Key key;

	public TokenProvider(
			@Value("${jwt.secret}") String secret, 
			@Value("${jwt.token-validity-in-seconds}") long tokenValidityInMillseconds) {
		this.secret = secret;
		this.tokenValidityInMillseconds = tokenValidityInMillseconds * 1000;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	 public String createToken(Authentication authentication) {
		 String authorities = authentication.getAuthorities().stream()
				 .map(GrantedAuthority::getAuthority)
				 .collect(Collectors.joining(","));
		 
		 long now = (new Date()).getTime();
		 Date validity = new Date(now + this.tokenValidityInMillseconds);
		 
		 return Jwts.builder()
				 .setSubject(authentication.getName())
				 .claim(AUTHORITIES_KEY, authorities)
				 .signWith(key, SignatureAlgorithm.HS512)
				 .setExpiration(validity)
				 .compact();
	 }
	 
	 
}
