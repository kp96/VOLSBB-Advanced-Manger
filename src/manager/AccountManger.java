package manager;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

public class AccountManger {
	private String userId;
	private String password;
	private String sessionCookie;

	public AccountManger(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public AccountManger() {

	}

	private static final String url = "http://115.248.50.60/registration/chooseAuth.do";

	public void login() {
		try {
			Response first = Jsoup
					.connect(
							"http://115.248.50.60/registration/Main.jsp?wispId=1")
					.timeout(10000).method(Method.GET).execute();

			Response login = Jsoup.connect(url).cookies(first.cookies())
					.userAgent("Mozilla/5.0").data("loginUserId", userId)
					.data("authType", "Pronto").data("loginPassword", password)
					.data("submit", "Login").method(Method.POST).timeout(10000)
					.execute();

			sessionCookie = login.cookie("JSESSIONID");

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionCookie() {
		return sessionCookie;
	}

	public void getUsageStatistics() {
		Response usage;
		try {
			usage = Jsoup.connect("http://115.248.50.60/registration/main.do?content_key=%2FCustomerSessionHistory.jsp")
					.cookie("JSESSIONID", sessionCookie)
					.method(Method.GET).timeout(20000).execute();
			Document details = usage.parse();
			Element e = details.body();
			// System.out.println(e);
			Elements f = e.select("b");
			System.out.println(f.get(6).text() + " " + f.get(7).text() + " "
					+ f.get(8).text() + " " + f.get(9).text() + " "
					+ f.get(10).text());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	public void changePassword(String newpwd){
		String pwdurl = "http://115.248.50.60/registration/changePassword.do";
		/*
		 * 	changeUserId:krishnapavankp
		 *	changePassword:kkp199678
		 *	changeNewPassword:kkp199678
		 	changeConfirmNewPassword:kkp199678
			submit:Update
		 */
		try {
			Response changePassword = Jsoup.connect(pwdurl).cookie("JSESSIONID", sessionCookie)
					.data("changeUserId",userId).data("changePassword",password).data("changeNewPassword",newpwd)
					.data("changeConfirmNewPassword",newpwd).data("submit","Update").method(Method.POST).execute();
			System.out.println(changePassword.parse().getElementsByClass("subHeaderCenter").get(1).text());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Network Error");
		}
				
	}
}
