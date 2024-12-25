package com.main.security.filter;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.main.security.service.JwtService;
import com.main.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
	private final JwtService jwtService;
	private final ApplicationContext context;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		String username=null;
		String token=null;
		if(authHeader != null && authHeader.startsWith("Bearer "))
		{
			token=authHeader.substring(7);
			username=jwtService.extractUsername(token);
		}

		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails details=context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
			if(jwtService.validate(token,details))
			{


				UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}

		}
		filterChain.doFilter(request, response);


	}

}
