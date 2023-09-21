package ca.sheridancollege.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandlerBean {

	
	public HandlerBean(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
	public HandlerBean(String ss, String hu2, String number, String email) {
		// TODO Auto-generated constructor stub
		
		this.entry_Number=ss;
		this.hu=hu2;
		this.hemail=email;
		this.hph=number;
	}
	
	public HandlerBean(String ss, String uname) {
		
		this.entry_Number = ss;
		this.hu = uname;
		// TODO Auto-generated constructor stub
	}

	private String hu;
	private String hp;
	private String hph;
	private String hemail;
	private String entry_Number;
	
	
}
