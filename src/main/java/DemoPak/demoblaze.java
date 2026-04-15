package DemoPak;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class demoblaze 
{

	public static void main(String[] args) 
	
	{
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://demoblaze.com/");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=\"Log in\"]"))).click();
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"loginusername\"]"))).sendKeys("sriram123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"loginpassword\"]"))).sendKeys("12345");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Log in\"]"))).click();
		
		String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Welcome sriram123\"]"))).getText();
		
		String expexted = "Welcome sriram123";
		
		if (actual.equals(expexted))
		{
			System.out.println("Login Successful");
		}
		
		else
		{
			System.out.println("Login Unsuccessful");
		}
		
		// Q2
		
		Actions act =new Actions (driver);
		
		WebElement lap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Laptops\"]")));
		
		act.moveToElement(lap).click().build().perform();
		
		String demo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"MacBook air\"]"))).getText();
		
		List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[id=\"tbodyid\"] div div div h4 ")));
		
		List<String> lapTops = new ArrayList<>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		
		for (WebElement text : list)
		{
			String txt = js.executeScript("return arguments[0].innerText", text).toString();
			
			lapTops.add(txt);
		}
		
		Collections.sort(lapTops);
		
		Set<String> productSet = new LinkedHashSet<>(lapTops);

		
		for (String str : productSet)
		{
			System.out.println("FOUND "+str);
		}
		
		WebElement macbook = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"MacBook Pro\"]")));
		
		act.scrollToElement(macbook).perform();
		
		if (macbook.getText().equals("MacBook Pro")) 
		{
            System.out.println("MacBook Pro verified successfully");
        }	
		
		//Q3
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"MacBook Pro\"]"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Add to cart\"]"))).click();
		
		wait.until(ExpectedConditions.alertIsPresent());

		Alert alt = driver.switchTo().alert();

		System.out.println(alt.getText()); 

		alt.accept();

		System.out.println("Product added successfully\nMacBook Pro added to cart.");
		
		driver.navigate().to("https://demoblaze.com/cart.html");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Place Order\"]"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"name\"]"))).sendKeys("sriram");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"country\"]"))).sendKeys("INDIA");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"city\"]"))).sendKeys("salem");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"card\"]"))).sendKeys("sriram");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"month\"]"))).sendKeys("jan");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"month\"]"))).sendKeys("year");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Purchase\"]"))).click();
		
		
		
		WebElement order = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class=\"lead text-muted \"]")));
		
		if (order.isDisplayed()) {
			System.out.println("Order is placed successfully");
			
			String orderDetails = order.getText();
			
			System.out.println("Order Details:\n" + orderDetails);
		}

		else {
			System.out.println("Order is Unsuccessful");
		}
		
		driver.quit();
	}

}
