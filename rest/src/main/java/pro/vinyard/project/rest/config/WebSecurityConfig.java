package pro.vinyard.project.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author VinYarD
 * created : 13/03/2018, 13:23
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	    http
			    .authorizeRequests()
			    .antMatchers("/").permitAll()
			    .anyRequest().authenticated()
		    .and()
			    .formLogin()
			    .loginPage("/login")
			    .permitAll()
			.and()
			    .logout()
			    .permitAll();
    }
	/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
		        .antMatchers("/admin/**").hasRole("ADMIN").and().formLogin();
        http.authorizeRequests().antMatchers("/").permitAll()
		        .antMatchers("/connection/**").hasRole("USER").and().formLogin();
        http.authorizeRequests().antMatchers("/").permitAll()
		        .antMatchers("/connection/**").hasRole("ADMIN").and().formLogin();
        http.csrf().disable();
    }
    */
    
    @Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("admin")
				.password("admin")
				.roles("USER");
	}

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN", "USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }
}