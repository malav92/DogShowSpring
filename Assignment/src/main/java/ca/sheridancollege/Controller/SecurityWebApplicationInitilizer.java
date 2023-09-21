package ca.sheridancollege.Controller;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitilizer extends AbstractSecurityWebApplicationInitializer {
	
	public SecurityWebApplicationInitilizer() {
		
		super(SecurityConfig.class);
	}

}
