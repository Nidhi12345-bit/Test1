package seleniumproject.Test;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class Test {
		
		public static void main(String[] args) {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
			driver.findElement(By.xpath("//input[@id='login-button']")).click();
			 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
	            List<WebElement> products = driver.findElements(By.className("inventory_item"));
	            String productName = "Sauce Labs Fleece Jacket";
	            Optional<WebElement> productOptional = products.stream()
	            	    .flatMap(product -> product.findElements(By.tagName("a")).stream())
	            	    .filter(link -> link.getText().contains(productName))
	            	    .findFirst();
	            if (productOptional.isPresent()) {
	                productOptional.get().click();
	            }
	            driver.findElement(By.id("add-to-cart")).click();
	            driver.findElement(By.className("shopping_cart_link")).click();
	            driver.findElement(By.id("checkout")).click();
	            driver.findElement(By.id("first-name")).sendKeys("Nidhi");
	            driver.findElement(By.id("last-name")).sendKeys("Chaudhary");
	            driver.findElement(By.id("postal-code")).sendKeys("123456789");
	            driver.findElement(By.id("continue")).click();
	            String paymentInformation = driver.findElement(By.xpath("//div[@data-test='payment-info-value']")).getText();
	            System.out.println("Payment Information: " + paymentInformation);
	            String totalPrice = driver.findElement(By.xpath("//div[@data-test='total-label']")).getText();
	            System.out.println("Total Price: " + totalPrice);
	         
	            driver.findElement(By.id("finish")).click();
	            driver.close();
	   
	
			
		   
		    
			
		}
	}