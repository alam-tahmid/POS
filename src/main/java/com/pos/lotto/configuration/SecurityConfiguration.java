	package com.pos.lotto.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.
			jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.
			authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/test").permitAll()
			.antMatchers("/home").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/printInvoice").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/saveOrder").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/getAllOrders").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/getAllOrders/**").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/getAllOrdersData").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/generatePdf/**").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/rate").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/profile").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/invoiceGeneration").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/sales").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/getProductDetails").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/updatePassword").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/expense").hasAnyAuthority("ADMIN","USER")
			.antMatchers("/admin/admin").hasAnyAuthority("ADMIN")
			.antMatchers("/uploadFile").hasAnyAuthority("ADMIN")
			.antMatchers("/fileUpload").hasAnyAuthority("ADMIN")
			.antMatchers("/product").hasAnyAuthority("ADMIN")
			.antMatchers("/addProduct").hasAnyAuthority("ADMIN")
			.antMatchers("/getInventory").hasAnyAuthority("ADMIN")
			.antMatchers("/allSales").hasAnyAuthority("ADMIN")
			.antMatchers("/download.xls").hasAnyAuthority("ADMIN")
			.antMatchers("/expenses").hasAnyAuthority("ADMIN")
			.antMatchers("/expense/**").hasAnyAuthority("ADMIN")
			.antMatchers("/registration").hasAnyAuthority("ADMIN")
			.anyRequest()
			.authenticated().and().csrf().disable().formLogin()
			.loginPage("/login").failureUrl("/login?error=true")
			.defaultSuccessUrl("/home")
			.usernameParameter("email")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").and().exceptionHandling()
			.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.
			ignoring()
			.antMatchers("/resources/**","/static/**","/css/**", "/js/**", "/images/**");
	}
}
