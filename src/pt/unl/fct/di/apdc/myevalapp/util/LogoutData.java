package pt.unl.fct.di.apdc.myevalapp.util;

public class LogoutData {
	
	public String username;
	public AuthToken token;
	
	public LogoutData(String username, AuthToken token) {
		this.username = username;
		this.token = token;
	}

}
