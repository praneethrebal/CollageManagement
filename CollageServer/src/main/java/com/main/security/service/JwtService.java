package com.main.security.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.main.credientials.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
	String key="";
	public JwtService()
	{
		KeyGenerator keyGenerator;
		try {
			keyGenerator=KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk=keyGenerator.generateKey();
			key=Base64.getEncoder().encodeToString(sk.getEncoded());

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public String generateToken(User user) {
//		User user=new User();
		Map<String, Object> claims = new HashMap<>();
		claims.put("role",user.getRole().name());

		return Jwts.builder()
					.claims()
					.add(claims)
					.subject(user.getRoll_no())
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis()+10*60*60*60))
					.and()
					.signWith(getKey())
					.compact();
	}

	private SecretKey getKey() {
		byte[] keybytes=Decoders.BASE64.decode(key);
		return Keys.hmacShaKeyFor(keybytes);
	}

	public String extractUsername(String token) {

		return extractClaim(token,Claims::getSubject);
	}



	private <T>T extractClaim(String token, Function<Claims,T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parser()
					.verifyWith(getKey())
					.build()
					.parseSignedClaims(token)
					.getPayload();
	}

	public boolean validate(String token, UserDetails details) {
		final String userName=extractUsername(token);

		return ((userName.equals(details.getUsername()))  && !isTokenExpried(token));
	}

	private boolean isTokenExpried(String token) {
		return extractExpration(token).before(new Date());
	}

	private Date extractExpration(String token) {

		return extractClaim(token,Claims::getExpiration);
	}

}
