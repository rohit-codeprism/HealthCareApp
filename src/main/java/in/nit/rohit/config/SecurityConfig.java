package in.nit.rohit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import in.nit.rohit.service.constant.UserRoles;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Authentication Object
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
		
		
		super.configure(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Authorize URL
		http.authorizeRequests()
		//.antMatchers("/patient/register","/patient/save").permitAll()
		//.antMatchers("/patient/all").hasAuthority(UserRoles.ADMIN.name())
		//.antMatchers("/doctor/**").hasAuthority(UserRoles.ADMIN.name())
		//.antMatchers("/spec/**").hasAuthority(UserRoles.ADMIN.name())
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.defaultSuccessUrl("/spec/all",true)
		
		.and()
		.logout();
		
		// FORM Configuration 
		
		// Logout details
		
		// exception  Handling 
		 
	}
	
}
