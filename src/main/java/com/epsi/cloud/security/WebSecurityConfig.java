package com.epsi.cloud.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.epsi.cloud.ServiceUsersInterface;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public ServiceUsersInterface serviceUsersInterface;
	@Autowired
	DataSource datasource;
	String findUserQuery = "select u.username,u.password, u.enabled "
			+ "from users u " + "where username = ?";
	String findRoleQuery = "select username, 'ADMIN'"
			+ " from users where username=?";
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);        		
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// authentification des users en Base de données 
    	auth.jdbcAuthentication()
    		.dataSource(datasource)
    		.usersByUsernameQuery(findUserQuery)
    		.authoritiesByUsernameQuery(findRoleQuery);  	
    }
	public ServiceUsersInterface getServiceUsersInterface() {
		return serviceUsersInterface;
	}
    @Autowired
	public void setServiceUsersInterface(ServiceUsersInterface serviceUsersInterface) {
		this.serviceUsersInterface = serviceUsersInterface;
	}       
}