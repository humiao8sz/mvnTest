package webmagic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class MySeleniumDownloaderLogin{

	private Logger logger = Logger.getLogger(getClass());

	public static void main(String args[]) throws InterruptedException, IOException{
		System.setProperty("selenuim_config", "D:\\workspace2\\mvnTest\\config.ini");
		System.getProperties().setProperty("webdriver.chrome.driver","D:\\workspace2\\mvnTest\\chromedriver.exe");
		MyWebDriverPool webDriverPool = new MyWebDriverPool(1,"");
		WebDriver webDriver = webDriverPool.get();
		webDriver.get("https://www.zhihu.com/signup?next=%2F");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		webDriver.manage().window().maximize();
		WebElement element = webDriver.findElement(By.xpath("//div[@class='SignContainer-switch']//span"));
        Actions builder = new Actions(webDriver);
        Action mouserOverlogin = builder.moveToElement(element).click().build();
        mouserOverlogin.perform();
        webDriver.findElement(By.name("username")).sendKeys("15851422729");
        webDriver.findElement(By.name("password")).sendKeys("hm5536857");
        
        WebElement button = webDriver.findElement(By.xpath("//button[@class='Button SignFlow-submitButton Button--primary Button--blue']"));
        Action login = builder.moveToElement(button).click().build();
        login.perform();
        
        WebElement ele = webDriver.findElement(By.xpath("//img[@class='Captcha-englishImg']"));
        // Get entire page screenshot
        File screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);
        // Get the location of element on the page
        Point point = ele.getLocation();
        // Get width and height of the element
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();
        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);
        // Copy the element screenshot to disk
        File screenshotLocation = new File("D:\\workspace2\\mvnTest\\test.png");
        FileUtils.copyFile(screenshot, screenshotLocation);
        
        
        //String code="";        
        //webDriver.findElement(By.xpath("//div[@class='SignFlowInput-errorMask Captcha-requiredErrorMessage Captcha-errorMessage SignFlowInput-requiredErrorMask']")).sendKeys(code);
	}
}
