package login;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

public class test {
	private static final String url = "http://phc.prontonetworks.com/cgi-bin/authlogin";
	public static void main(String[] args) {
		String userid,pwd;
		Scanner sc = new Scanner(System.in);
		System.out.print("User name and password");
		userid = sc.next();
		pwd = sc.next();
		try{
		Response login = Jsoup.connect(url).userAgent("Mozilla/5.0").data("userId",userid)
				.data("password",pwd).data("serviceName","ProntoAuthentication").data("Submit22","Login")
				.method(Method.POST).timeout(20000).execute();
		if(login.parse().title().equals("Successful Pronto Authentication")){
			System.out.print("Logged In");
		}
		else{
			System.out.print("Some Error");
		}
			
		}catch(Exception e){
			System.out.print("Network Error");
		}
	}
}
