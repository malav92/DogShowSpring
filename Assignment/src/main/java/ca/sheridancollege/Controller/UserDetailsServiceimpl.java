package ca.sheridancollege.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.DAO.DAO;


@Service
public class UserDetailsServiceimpl implements UserDetailsService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		ca.sheridancollege.Bean.HandlerBean handler = DAO.findUserAccount(username);
		
		System.out.println(username);
		if(handler ==null) {
			
			System.out.println("User not found!" +username);
			throw new UsernameNotFoundException("User" +username
					+"was not found in database");
		}
		
		System.out.println("Found User" +handler);
		
		ArrayList<String> roles = DAO.getRoleName();
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		if(roles != null) {
			
			for(String role : roles) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
				
			}
		
		}              
		
		UserDetails userDetails = (UserDetails) new User(handler.getHu(), handler.getHp(),grantList);
		return userDetails;
	}

}
