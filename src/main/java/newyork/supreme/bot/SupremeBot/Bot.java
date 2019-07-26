package newyork.supreme.bot.SupremeBot;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

//WATCH FOR TIMING
// AND COOKIES (MAY HAVE TO INJECT MYSELF)

public class Bot extends Item {
	private static String baseUrl;
	private ChromeOptions options;
	private WebDriver driver;
	private JavascriptExecutor js;
	private String baseURL;
	private String checkoutURL;
	
	public Bot() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		this.driver = new ChromeDriver(options);
		this.js = (JavascriptExecutor)driver;
		this.baseURL = "https://www.supremenewyork.com/shop/all";
		this.checkoutURL = "https://www.supremenewyork.com/checkout";
	}
	String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36";
	
	
	public void ProxyUsingChromeDriver(String localhost) {
		//Set the location of the ChromeDriver
		System.setProperty("webdriver.chrome.driver", "/Users/lukegrippa/Selenium/chromedriver");
		//Create a new desired capability
		ChromeOptions option = new ChromeOptions();		
		// Create a new proxy object and set the proxy
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(localhost); // "localhost:8888"
		//Add the proxy to our capabilities  
		option.setCapability(CapabilityType.PROXY, proxy);
		//Start a new ChromeDriver using the capabilities object we created and added the proxy to
		ChromeDriver Driver = new ChromeDriver(option);
		//Navigation to a url and a look at the traffic logged in fiddler
		Driver.navigate().to("http://bbc.co.uk");
	}
	
	public void switchHeadless() {
		Set<Cookie> cookies = driver.manage().getCookies(); //returns all cookies, lots of other cookie related methods exists
		driver.quit();
		WebDriver driver = new ChromeDriver(); // not headless for checkout
		for (Cookie in cookies) {
			driver.manage().addCookie(Cookie);
		}
		driver.get(checkoutURL); // go to checkout and have everythng already added to the cart
		
	}



	public static void main(String[] args) {
		// declaration and instantiation of objects/variables
		
		
		Bot bot = new Bot();
		
		launchSupreme(bot); // open the Supreme store
		bot.driver.manage().window().maximize(); // maximize the window
	
		Billing billingInfo = new Billing();
		Item socks = new Item("jackets", "Floral Silk Track Jacket", "Mint", "Large");
		
		bot.addItemToCart(bot, socks.getType(), socks.getName(), socks.getColor(), socks.getSize());
		bot.driver.navigate().to(bot.checkoutURL);
		bot.checkout(bot, billingInfo);
		
		 // Take a screenshot of the current page
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("/Users/lukegrippa/Desktop/Supreme Bot Screenshots/screenshot.png"));
		
//		driver.quit();  // close Chrome
	}
	
	
	// launch Chrome and direct it to the Supreme store
	public static void launchSupreme(Bot bot) {
		bot.driver.get(bot.baseURL); 
		try { // wait for page to load
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void URLGen() {
		
	}
	
	// find item and add it to you cart
	public static void addItemToCart(Bot bot, String type, String name, String color, String s) {
		bot.driver.findElement(By.linkText(type)).click(); // find and click on the type of the clothing link
		
		try { // wait for page to load
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try { // try to find the element
			bot.driver.findElement(By.partialLinkText(name)).click(); // find and click on the name of the clothing link
			
		} catch (NoSuchElementException e) { // couldnt find the element so now we are going to scroll
			e.printStackTrace();
			System.out.println("couldn't find item, need to scroll.");
			bot.js.executeScript("arguments[0].scrollIntoView(true);", bot.driver.findElement(By.partialLinkText(name))); // scroll to the item
			
			try { // wait for page to load
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			bot.driver.findElement(By.partialLinkText(name)).click(); // find and click on the name of the clothing link
			
		}
		
		bot.driver.findElement(By.partialLinkText(color)).click(); // find and click on the color img link
		
		try { // wait for page to load
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Select size = new Select(bot.driver.findElement(By.id("s"))); // select size drop down 
		try { // wait for page to load
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		size.selectByVisibleText(s); // select the actual size
		try { // wait for page to load
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		bot.driver.findElement(By.name("commit")).click(); // find and click add to cart button
	}
	
	// navigate to checkout screen, fill out, and purchase item
	public static void checkout(Bot bot, Billing bi) {
		bot.driver.navigate().to(bot.checkoutURL); // navigate to the checkout screen
		
		try { // wait for page to load
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		WebElement billingName = bot.driver.findElement(By.id("order_billing_name"));
		billingName.click();
		billingName.sendKeys(bi.getName());
		
		WebElement email = bot.driver.findElement(By.id("order_email"));
		email.click();
		email.sendKeys(bi.getEmail());
		
		WebElement tel = bot.driver.findElement(By.id("order_tel"));
		tel.click();
		tel.sendKeys(bi.getTel());
		
		WebElement address = bot.driver.findElement(By.id("bo"));
		address.click();
		address.sendKeys(bi.getAddress());
		
		WebElement apt = bot.driver.findElement(By.id("oba3"));
		apt.click();
		apt.sendKeys(bi.getApt());
		
		WebElement zip = bot.driver.findElement(By.id("order_billing_zip"));
		zip.click();
		zip.sendKeys(bi.getZip());
		
		WebElement city = bot.driver.findElement(By.id("order_billing_city"));
		city.click();
		city.sendKeys(bi.getCity());
		
		Select state = new Select(bot.driver.findElement(By.id("order_billing_state"))); // select state drop down 
		state.selectByVisibleText(bi.getState()); // select the actual state
		
//		Select country = new Select(bot.driver.findElement(By.id("order_billing_state"))); // select state drop down 
//		country.selectByVisibleText(bi.getCountry()); // select the actual state
		
		WebElement ccnumber = bot.driver.findElement(By.id("nnaerb"));
	    ccnumber.click();
	    ccnumber.sendKeys(bi.getCvv());
		
		Select ccmonth = new Select(bot.driver.findElement(By.id("credit_card_month"))); // select ccmonth drop down
		ccmonth.selectByVisibleText(bi.getCcmonth()); // select the actual month
		
		Select ccyear = new Select(bot.driver.findElement(By.id("credit_card_year"))); // select ccyear drop down
		ccyear.selectByVisibleText(bi.getCcyear()); // select the actual year
		
	    WebElement cvv = bot.driver.findElement(By.id("orcer"));
	    cvv.click();
	    cvv.sendKeys(bi.getCvv());
	    
	    bot.driver.findElement(By.className("iCheck-helper")).click(); // find and click agree to T&C
	    
	    bot.driver.findElement(By.name("commit")).click(); // find and click process payment
		

	}
	
	//client.addRequestHeader("User-Agent", getRandomUseragent());
	private static String getRandomUseragent(){
		List<String> userAgents = new ArrayList<String>();
		Random rand = new Random();
		try (BufferedReader br = new BufferedReader(new FileReader("FILENAME"))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				userAgents.add(sCurrentLine);
			}
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return userAgents.get(rand.nextInt(userAgents.size())); 
	}
	
//	// PROXY + RANDOM USER AGENT
//	WebClient webClient = new WebClient();
//	ProxyConfig prc = new ProxyConfig("localhost", 9150, true); webClient.getOptions().setProxyConfig(prc);
}