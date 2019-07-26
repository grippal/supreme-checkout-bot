package newyork.supreme.bot.SupremeBot;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Billing {
	private String name;
	private String email;
	private String tel;
	private String address;
	private String apt;
	private String zip;
	private String city;
	private String state;
	private String country;
	private String ccyear;
	private String ccnumber;
	private String ccmonth;
	private String cvv;
	
	// make sure you click "I have read and accept T&C"
	
	public Billing() { 
		this.name = "Jaquory a bitch";
		this.email = "Jaquoryabitch@yomama.com";
		this.tel = "1234567890";
		this.address = "Jaquory a bitch";
		this.apt = "Jaquory a bitch";
		this.zip = "Jaquory a bitch";
		this.city = "Jaquory a bitch";
		this.state = "AL";
		this.country = "USA";
		this.ccnumber = "Jaquory a bitch";
		this.ccmonth = "01";
		this.ccyear = "2019";
		this.cvv = "Jaquory a bitch";
		
	}
	
	public Billing(String json) {
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
		
		this.name = JsonPath.read(document, "$.store.book[0].name");
		this.email = JsonPath.read(document, "$.store.book[0].email");
		this.tel = JsonPath.read(document, "$.store.book[0].tel");
		this.address = JsonPath.read(document, "$.store.book[0].address");
		this.apt = JsonPath.read(document, "$.store.book[0].apt");
		this.zip = JsonPath.read(document, "$.store.book[0].zip");
		this.city = JsonPath.read(document, "$.store.book[0].city");
		this.state = JsonPath.read(document, "$.store.book[0].state");
		this.country = JsonPath.read(document, "$.store.book[0].country");
		this.ccnumber = JsonPath.read(document, "$.store.book[0].ccnumber");
		this.ccmonth = JsonPath.read(document, "$.store.book[0].ccmonth");
		this.ccyear = JsonPath.read(document, "$.store.book[0].ccyear");
		this.cvv = JsonPath.read(document, "$.store.book[0].cvv");
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCcyear() {
		return ccyear;
	}

	public void setCcyear(String ccyear) {
		this.ccyear = ccyear;
	}

	public String getCcnumber() {
		return ccnumber;
	}

	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
	}

	public String getCcmonth() {
		return ccmonth;
	}

	public void setCcmonth(String ccmonth) {
		this.ccmonth = ccmonth;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

}
