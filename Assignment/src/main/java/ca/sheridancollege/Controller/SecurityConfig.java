package ca.sheridancollege.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private LoginAccessDeniedHandler accessDenied;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/hanlerPage/**").hasRole("HANDLER")
		.antMatchers(HttpMethod.POST, "/hanlerPage").permitAll()
		.antMatchers("/", "/js/**","/css/**", "/img/**","/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
	    .logout()
	    .invalidateHttpSession(true)
	    .clearAuthentication(true)
	    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    .logoutSuccessUrl("/login?logout").permitAll()
        .and()
        .exceptionHandling().accessDeniedHandler(accessDenied);
		
	}
	
	
	@Autowired
	private UserDetailsServiceimpl userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder Passwordencoder () {
		
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailsService).passwordEncoder(Passwordencoder());
	}

}
