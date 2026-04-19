package Pak1;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;



public class Program1 {

	public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException 
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	
		
		
		
	}
	public void method9(WebDriver driver) throws IOException, URISyntaxException 
	{
	SoftAssert a = new SoftAssert();
	//https://rahulshettyacademy.com/AutomationPractice/
		WebElement footerLink = driver.findElement(By.cssSelector("div[class*='footer_top_agile_w3ls']"));
		List<WebElement> links = footerLink.findElements(By.tagName("a"));
		
			for(WebElement link: links)
				{
				String url = link.getAttribute("href");
				if(!(url==null) ||url.isEmpty())
				{
					HttpURLConnection conn = (HttpURLConnection) new URI(url).toURL().openConnection();
					conn.setRequestMethod("HEAD");
					conn.connect();
					int respCode = conn.getResponseCode();
					
				a.assertTrue(respCode>400,"broken link is: + url");
				
				}
				}
			a.assertAll();
	}
	
	public void method8(WebDriver driver) throws IOException 
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("E:\\Screenshots\\screenshot1.png"));
		
	}
	
	public void method7(WebDriver driver) 
	{
		//https://rahulshettyacademy.com/AutomationPractice/
		WebElement element = driver.findElement(By.xpath("//div[@class='tableFixHead']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element); //scroll windows
		
		js.executeScript("document.querySelector('div.tableFixHead').scrollTop =5000");
		
		//document.querySelector("div.tableFixHead").scrollTop=5000;
		
		//document.querySelector("div.tableFixHead").scrollLeft=5000; - horizontal scroll	
	}
	public void method6(WebDriver driver) throws InterruptedException
	{
	//https://rahulshettyacademy.com/AutomationPractice/
		int size = driver.findElements(By.tagName("a")).size();
		System.out.println("size is :" + size);
		
		WebElement footer = driver.findElement(By.cssSelector("div[class*='footer_top_agile_w3ls']"));
		int size1 = footer.findElements(By.tagName("a")).size();
		System.out.println("size of footer is :" + size1);
		
		WebElement coloumn = driver.findElement(By.xpath("//div[contains(@class,'footer_top_agile_w3l')]/table/tbody/tr/td[1]"));
		int columnsize = coloumn.findElements(By.tagName("a")).size();
		
		Actions action = new Actions(driver);
		String openEachLink = Keys.chord(Keys.CONTROL,Keys.ENTER);
		for(int i=1;i<=columnsize-1;i++)
		{
			coloumn.findElements(By.tagName("a")).get(i).sendKeys(openEachLink);
			Thread.sleep(2000);
		}
		
		Set <String> Allwindows = driver.getWindowHandles();
		Iterator<String> it = Allwindows.iterator();
		while(it.hasNext())
		{
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}
		driver.quit();
		
		
	}
	
	
	public void method5(WebDriver driver)
	{
driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src*='/resources/demos/droppable']")));
		
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement destination  = driver.findElement(By.id("droppable"));
		action.dragAndDrop(source, destination).build().perform();
		driver.switchTo().defaultContent();
		
	}
	
	public void method4(WebDriver driver)
	{
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.id("nav-link-accountList"));
		action.moveToElement(element).build().perform();
		
		WebElement ele = driver.findElement(By.id("twotabsearchtextbox"));
		action.click(ele).keyDown(Keys.SHIFT).sendKeys("hello").keyUp(Keys.SHIFT).build().perform();
		
	}
		
		public void method3(WebDriver driver) throws InterruptedException
		{
			String[] itemsNeeded = {"Cauliflower","Beetroot"};
			List needed = Arrays.asList(itemsNeeded);
			
			List<WebElement> products = driver.findElements(By.cssSelector("div.product h4.product-name"));
			for(int i=0;i<products.size();i++)
			{
				String each = products.get(i).getText().split("-")[0].trim();
				
				
				System.out.println(each);
				if(needed.contains(each))
				{
					driver.findElements(By.cssSelector("div[class='product-action'] button")).get(i).click();
					Thread.sleep(2000);
				} 
			}
		}
		
		
        
	
	
	public void method2(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.id("name")).sendKeys("Rahul");
        driver.findElement(By.id("alertbtn")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        
        driver.findElement(By.id("opentab")).click();
        String parentWindow = driver.getWindowHandle();
       Set <String> childWindows = driver.getWindowHandles(); 
       for(String eachWindow: childWindows)
       {
    	   if(!eachWindow.equals(parentWindow))
    	   {
    		   driver.switchTo().window(eachWindow);
    	   }
       }
       driver.switchTo().window(parentWindow);
		System.out.println("hello");
       
		List<WebElement> elem =  driver.findElements(By.xpath("//input[@type='checkbox']"));
		for(WebElement e : elem)
		{
			System.out.println(e.getText());
			if(e.getAttribute("value").contains("option2"))
			{
				Thread.sleep(2000);
				e.click();
				
			}	
		}	
	}
	
	
	
	public void method1(WebDriver driver) throws InterruptedException
	{
		WebElement ele = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select s = new Select(ele);
		s.selectByIndex(1);
		
		driver.findElement(By.id("autosuggest")).sendKeys("ind");
		List <WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		Thread.sleep(2000);
		for(WebElement option : options)
		{
			if(option.getText().equalsIgnoreCase("india"))
			{
				option.click();
				break;
			}
		}
		
	}

}
