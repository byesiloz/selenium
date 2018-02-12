import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebScrapper {

	public WebDriver driver = new FirefoxDriver();

	static int aa = 0;
	static int bb = 1;

	public void closeBrowser() {
		System.out.println("5");
		driver.close();
	}

	public void scenario1() throws InterruptedException {
		driver.navigate().to("https://www.enuygun.com/ucak-bileti/sikca-sorulan-sorular/");
		forScenario1(aa, bb);
	}

	public void forScenario1(int a, int b) throws InterruptedException {

		WebElement dropDown = driver.findElement(By.xpath("//a[@href='#topic" + b + "']"));
		dropDown.click();
		WebElement disdiv = driver.findElement(By.id("topic" + b + ""));
		List<WebElement> allOptions = disdiv.findElements(By.xpath(".//a"));

		// dropDown.sendKeys( Keys.DOWN ); //simulate visual movement

		System.out.println("yazdýrýldý" + allOptions.get(a).getText());

		WebElement alar = allOptions.get(a);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", alar);

		Thread.sleep(3000);
		driver.navigate().back();

		int size = allOptions.size();
		a = a + 1;
		if (a < size) {
			forScenario1(a, b);

		} else {
			a = 0;
			b = b + 1;
			if (b < 11)
				forScenario1(a, b);
		}

	}

	public void scenario3() throws InterruptedException {

		// Open web site and maximize window
		driver.get("https://www.enuygun.com/otel/");

		// Find and click on a random city
		List<WebElement> allCities = driver.findElements(By.xpath("//*[@class='panel otel-panel']/div[2]//li//a"));
		Random rand = new Random();
		int randomProduct = rand.nextInt(allCities.size());

		WebElement cities = allCities.get(randomProduct);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", cities);

		// allCities.get(randomProduct).click();
		System.out.println("yazdýrýldý:" + allCities.get(randomProduct).getText());
	
		
		
	}  
	
	public void scenario5() throws InterruptedException {
		
		scenario3();
		
        Thread.sleep(5000);
		
		datePicker();
		
		driver.findElement(By.xpath("//*[@id=\"search-form\"]/div[4]/button")).click();
		
		Thread.sleep(6000);
		
		driver.findElement(By.xpath("//html/body/div[2]/div[5]/div/div[1]/div[2]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//html/body/div[2]/div[5]/div/div[1]/div[2]/ul/li[1]")).click();
		
		
		Thread.sleep(5000);
		
		WebElement button = driver.findElement(By.xpath("//*[@class='otel-list']/li[1]/div[1]/div[3]/div[3]/a"));
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", button);
		
		
	}
	
	public void scenario4() throws InterruptedException {
		
		scenario3();
		
        Thread.sleep(5000);
	
		List<WebElement> otelList = driver.findElements(By.xpath("//*[@class='otel-list']/li"));
		for(int i =0; i<otelList.size(); i++)
		{
			System.out.println("Oteller:" + otelList.get(i).getText());
		}
		int number = (int) (Math.random() * (otelList.size()) - 1);
		driver.findElement(By.xpath("//*[@class='otel-list']/li["+ number +"]/div[1]/div[2]/h2/a")).click();
		
		
		List<String> windowHandles1 = new ArrayList<String>(driver.getWindowHandles());
		for (String handle : windowHandles1) 
		{
		driver.switchTo().window(handle);
		if ("title".equals(driver.getTitle())) {
		//do your operations
			Thread.sleep(5000);
		datePicker();
		}
		}
		

		
	}

	public void datePicker() throws InterruptedException {

		// Click and open the datepickers

		driver.findElement(By.xpath("//*[@id='checkin-checkout-date']")).click();
		
		Thread.sleep(3000);

		List<WebElement> listDays = driver.findElements(By.xpath("//*[@class='date-picker-wrapper no-shortcuts  custom-topbar no-gap two-months']/div[2]/table/tbody/tr/td/div[@class ='day toMonth  valid']"));

		
		int number = (int) (Math.random() * (listDays.size()) - 1);
		System.out.println("Birinci Tarih:" + listDays.get(number).getText());
		listDays.get(number).click();
		
		Thread.sleep(3000);
		
		List<WebElement> listDays2 = driver.findElements(By.xpath("//*[@class='date-picker-wrapper no-shortcuts  custom-topbar no-gap two-months']/div[2]/table/tbody/tr/td/div[@class ='day toMonth  valid tmp']"));
		
		
		int number2 = (int) (Math.random() * (listDays2.size()) - 1);
		System.out.println("Ýkinci Tarih:" + listDays2.get(number2).getText());
		listDays2.get(number2).click();

	}    
												
	public void scenario2() throws InterruptedException {

		String fromCity;
		String toCity;

		driver.get("https://www.enuygun.com/ucak-bileti/");

		fromCity = randCity();

		driver.findElement(By.xpath("//*[@id='from-input']")).sendKeys("" + fromCity + "");

		Thread.sleep(3000);

		List<WebElement> listItems = driver.findElements(By.xpath("//html/body/ul[1]"));

		listItems.get(0).click();

		toCity = randCity();

		while (fromCity.equals(toCity)) {
			toCity = randCity();

		}

		driver.findElement(By.xpath("//*[@id='to-input']")).sendKeys("" + toCity + "");

		Thread.sleep(3000);

		List<WebElement> listItems2 = driver.findElements(By.xpath("//html/body/ul[2]"));

		listItems2.get(0).click();

		// Click and open the datepickers

		driver.findElement(By.xpath("//*[@id='departure-date-input']")).click();

		Thread.sleep(3000);

		List<WebElement> listDays = driver.findElements(
				By.xpath("//*[@id=\"ui-datepicker-div\"]/div/table/tbody/tr/td[@data-handler='selectDay']"));
		int number = (int) (Math.random() * (listDays.size()) - 1);
		listDays.get(number).click();

		driver.findElement(By.xpath("//*[@id='return-date-input']")).click();

		Thread.sleep(3000);

		List<WebElement> listDays2 = driver.findElements(
				By.xpath("//*[@id=\"ui-datepicker-div\"]/div/table/tbody/tr/td[@data-handler='selectDay']"));
		int number2 = (int) (Math.random() * (listDays2.size()) - 1);
		listDays2.get(number2).click();

		driver.findElement(By.xpath("//*[@id='flight-direct']")).click();
		driver.findElement(By.xpath("//*[@id='select-passenger-btn']")).click();
		driver.findElement(By.xpath("//*[@id=\"flight-class-select\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"flight-class-select\"]/option[@value='business']")).click();
		driver.findElement(By.xpath("//*[@id=\"passenger-selector-dropdown\"]/div[7]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"flight-search-form\"]/div[4]/div[2]/button")).click();

	}

	public static String randCity() {
		String[] cities = { "Ýzmir", "Ýstanbul", "Adana", "Antalya", "Trabzon" };
		int number = (int) (Math.random() * cities.length);
		System.out.println("rasgele sayi:" + number);
		System.out.println("Rasgele secilen sehir" + cities[number]);
		return cities[number];

	}

	public static void main(String[] args) throws IOException, InterruptedException {

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\BUKET\\Desktop\\geckodriver-v0.19.1-win64\\geckodriver.exe");

		WebScrapper webSrcapper = new WebScrapper();

		

		webSrcapper.scenario1();
		// webSrcapper.scenario2();
		// webSrcapper.scenario3();
		// webSrcapper.scenario4();
		// webSrcapper.scenario5();

	}
}