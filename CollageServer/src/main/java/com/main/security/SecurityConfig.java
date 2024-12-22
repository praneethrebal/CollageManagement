package com.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.main.security.filter.JwtFilter;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.authorizeHttpRequests(req -> req.requestMatchers("/login","/register","/error").permitAll()
										.requestMatchers("/admin/**").hasRole("ADMIN")
										.requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
										.anyRequest().authenticated());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.formLogin(Customizer-> Customizer.disable());
		http.csrf(csrf -> csrf.disable());
		http.formLogin(Customizer ->Customizer.disable());
		http.httpBasic(Customizer.withDefaults());
		http.cors(Customizer.withDefaults());
		return http.build();
	}


	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}

	@Bean
	public WebMvcConfigurer crosConfig()
	{
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry)
			{
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true)
						;
			}
		};
	}

//	@Bean
//	public WebMvcConfigurer crosConfig() {
//	    return new WebMvcConfigurer() {
//	        public void addCorsMappings(CorsRegistry registry) {
//	            registry.addMapping("/**")
//	                    .allowedOrigins("http://localhost:3000")  // Make sure this is correct
//	                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//	                    .allowedHeaders("*");
//	        }
//	    };
//	}
//

}
