package seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class webdriver {
	private WebDriver driver;
	private String baseUrl;
	String [] str = {"学号","姓名","github地址"};
	 File inFile;
    String inString ;
	
	@Before
	public void SetUp() throws Exception
	{
		
		inFile = new File("E:\\eclipse\\work\\seleniumtest\\inputgit.csv"); 
	    inString = "";
	}
	
	@Test
	public void io()
	{
		 try {
			 	int j=0;
	            BufferedReader reader = new BufferedReader(new FileReader(inFile));
	            while((inString = reader.readLine())!= null){
	            	if(j>=1)
	            	{
	            		driver = new FirefoxDriver();
	            		baseUrl="http://121.193.130.195:8080/";
	            		String[] sc=inString.split(",");
	            		String name=sc[0];
	            		String pwd=name.substring(4);
	            		String github=sc[2];
	            		driver.get(baseUrl);
	            		driver.findElement(By.id("name")).sendKeys(name);
	            		driver.findElement(By.id("pwd")).sendKeys(pwd);
	            		driver.findElement(By.id("submit")).click();
	            		WebElement result=driver.findElement(By.id("table-main"));
	            		List<WebElement> rows = result.findElements(By.tagName("tr"));
	            		List<WebElement> col =rows.get(2).findElements(By.tagName("td"));
	            		String regit=col.get(1).getText();
	            		int checked=1;
	            		for(int k=0;k<github.length();k++)
	            			if(github.charAt(k)!=regit.charAt(k))
	            				checked=0;
	            		if(checked==1)
	            			System.out.println(name+" checked");
	            		else
	            			System.out.println("error in "+name);
	            		driver.quit();
	            	}
	                j++;
	            }
	            reader.close();
	        } catch (FileNotFoundException ex) {
	            System.out.println("没找到文件！");
	        } catch (IOException ex) {
	            System.out.println("读写文件出错！");
	        }
	}
}
