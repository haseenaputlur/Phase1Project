package assignment;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonLaunch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement ProductSearch = driver.findElement(By.id("twotabsearchtextbox"));
		ProductSearch.sendKeys("samsung");
		WebElement Go = driver.findElement(By.id("nav-search-submit-button"));
		Go.click();

		List<WebElement> Results = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2"));
		List<WebElement> Price = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		System.out.println("Total results:" + Results.size());

		for (int i = 0; i < Results.size(); i++) {
			System.out.println(Results.get(i).getText() + "-- " + Price.get(i).getText());
		}

		String ParentWin = driver.getWindowHandle();

		WebElement FirstProduct = driver.findElement(By.xpath("//span[text()='Samsung Galaxy M13 (Aqua Green, 6GB, 128GB Storage) | 6000mAh Battery | Upto 12GB RAM with RAM Plus']"));
		FirstProduct.click();

		Set<String> allWins = driver.getWindowHandles();

		for (String win : allWins) {

			System.out.println(win);

			if (!win.equals(ParentWin)) {

				driver.switchTo().window(win);
			}
		}

		WebElement ProductDescription = driver.findElement(By.id("productTitle"));

		String Actual = ProductDescription.getText();
		String Expected = "Samsung Galaxy M13 (Aqua Green, 6GB, 128GB Storage) | 6000mAh Battery | Upto 12GB RAM with RAM Plus";

		System.out.println("Productdescription is: " + ProductDescription.getText());

		if (Actual.equals(Expected)) {
			System.out.println("TestCase Pass");
		} else {
			System.out.println("TestCase Fail");
		}

		driver.close();

		// driver.quit();

	}

}
