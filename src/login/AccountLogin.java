package login;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class AccountLogin {
	private String userId;
	private String password;
	private String sessionCookie;
	public AccountLogin(String userId, String password){
		this.userId = userId;
		this.password = password;
	}
	public AccountLogin(){
	
	}
	private static final String url = "http://115.248.50.60/registration/chooseAuth.do";
	public void login(){
		try{
			Response first = Jsoup.connect("http://115.248.50.60/registration/Main.jsp?wispId=1").timeout(10000).method(Method.GET)
					.execute();
			
			Response login = Jsoup.connect(url).cookies(first.cookies()).userAgent("Mozilla/5.0").data("loginUserId",userId)
					.data("authType","Pronto").data("loginPassword",password).data("submit","Login")
					.method(Method.POST).timeout(10000).execute();
			
			sessionCookie = login.cookie("JSESSIONID");
			
		}catch(Exception e){
			System.out.print(e);
		}
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getSessionCookie(){
		return sessionCookie;
	}
}
