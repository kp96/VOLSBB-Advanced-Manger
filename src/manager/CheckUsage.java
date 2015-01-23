package manager;

import java.io.IOException;

import login.AccountLogin;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CheckUsage {
	static AccountLogin r1;
	static String sessionCookie;
	private static final String url = "http://115.248.50.60/registration/main.do?content_key=%2FCustomerSessionHistory.jsp";
	public static void main(String[] args) throws IOException{
		AccountLogin r1 = new AccountLogin();
		r1.setUserId("krishnapavankp");
		r1.setPassword("kkp199678");
		r1.login();
		sessionCookie = r1.getSessionCookie();
		Response usage = Jsoup.connect("http://115.248.50.60/registration/main.do?content_key=%2FCustomerSessionHistory.jsp")
							.cookie("JSESSIONID", sessionCookie).method(Method.GET).timeout(20000).execute();
		
		
	}
}
