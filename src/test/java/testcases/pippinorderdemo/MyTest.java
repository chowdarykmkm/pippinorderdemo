package testcases.pippinorderdemo;
import org.testng.annotations.Test;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MyTest {
	@Test
	public void setup() throws Exception {
		String workingDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", workingDir + "/executables/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.pippintitle.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement iptemailaddress = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Email_Address']")));
		iptemailaddress.sendKeys("pippintitle_demotest@mailinator.com");
		WebElement iptpassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='User_Password']")));
		iptpassword.sendKeys("DemoTest#567#");
		WebElement btngo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='loginBtnLogin']")));
		jse.executeScript("arguments[0].click()", btngo);
		WebElement btnplaceorder = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Place Order']"))));
		jse.executeScript("arguments[0].click()", btnplaceorder);
		WebElement btnfullsrch = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(text(),'Full Search')]"))));
		jse.executeScript("arguments[0].click()", btnfullsrch);
		WebElement iptownername = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Property_First_Name']")));
		iptownername.sendKeys("Murali Chowdary");

		WebElement iptpropsearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search-box']")));
		jse.executeScript("arguments[0].scrollIntoView(true);", iptpropsearch);
		iptpropsearch.sendKeys("3485 Wineville");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".pac-item"))));
		iptpropsearch.sendKeys(Keys.DOWN);
		iptpropsearch.sendKeys(Keys.DOWN);
		iptpropsearch.sendKeys(Keys.ENTER);

		WebElement iptclientref = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Property_Order_Number']")));

		Date date = new Date();
		String[] datetime = new SimpleDateFormat("dd-MM-yyy HH:mm:ss").format(date).split(" ");
		iptclientref.sendKeys("Murali Chowdary" + "_" + datetime[0].trim());

		WebElement btnuploaddocs = driver.findElement(By.xpath("//button[contains(text(),'Upload Documents')]"));
		btnuploaddocs.click();
		Robot robot = new Robot();
		String documentsPath = '"'+ workingDir + "\\Doc1.pdf\"" + '"'+ workingDir + "\\Doc2.pdf\"" +'"'+ workingDir + "\\Doc3.pdf\"";        
		StringSelection str = new StringSelection(documentsPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_ENTER);

		spinnerHandlingAfterFilesUploaded(driver,wait,jse);
		spinnerHandlingAfterFilesUploaded(driver,wait,jse);                    

		WebElement btncontinue = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		jse.executeScript("arguments[0].click()", btncontinue);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(text(),'I accept')]//ancestor::div//label"))));
		jse.executeScript("arguments[0].click()", driver.findElement(By.xpath("//span[contains(text(),'I accept')]//ancestor::div//label")));

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Submit')]"))));
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(text(),'Submit')]")));
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='Order_ID']"))));
		String orderid = driver.findElement(By.xpath("//input[@id='Order_ID']")).getAttribute("ng-reflect-value");

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='ordDetBtnSendMsg']"))));
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='ordDetBtnSendMsg']")));

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea[@id='msg-area']")))).sendKeys(orderid + "Murali Chowdary" + new SimpleDateFormat("dd-MM-yyy HH:mm:ss").format(date));

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='msgSend']"))));
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='msgSend']")));	
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'msgFormat')]"))));

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//icon[@icon='arrow_drop_down']"))));
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//icon[@icon='arrow_drop_down']")));

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Logout')]"))));
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(text(),'Logout')]")));  
		
		driver.close();
	}
	private void   spinnerHandlingAfterFilesUploaded(WebDriver driver,WebDriverWait wait,JavascriptExecutor jse) {
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div//mat-spinner"), 1));
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div//mat-spinner"), 0));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//button//span//i[contains(text(),'delete')])[1]"))));
		jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//button//span//i[contains(text(),'delete')])[1]")));
		if (wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='conOk']")))).isDisplayed()) 
			jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='conOk']")));            

	}

}