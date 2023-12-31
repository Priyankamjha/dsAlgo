package dsAlgo_PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import dsAlgo_Utilities.PageUtils;

public class JTreePage {
	private WebDriver driver;
	public JTreePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	private String result;
	 @FindBy(xpath = "//ul/a[text()='Types of Trees']")private WebElement typeoftree;
	 @FindBy(xpath = "//a[text()='Try here>>>']")private WebElement tryhere;
	 @FindBy(xpath = "//button[text()='Run']" )private WebElement runbtn;
	 @FindBy(xpath = "//input[@value='Submit']" )private WebElement submitbtn;	
	 @FindBy(id = "output")private WebElement outputbox;
	 @FindBy(xpath = "//a[text()='Practice Questions']") WebElement practicequestionlink;
	 @FindBys(value = { @FindBy(how = How.XPATH,using="//div[contains(@style,'margin-bottom')]/ul")}) private List<WebElement> treemenu;
	 @FindBys(value = { @FindBy(how = How.XPATH,using="//div[@class='list-group']/a")}) private List<WebElement> practicemenu;
	 private PageUtils pageutil=new PageUtils();	
	
public void typeoftree_click() 
{  	pageutil.scrolldown(driver);
	pageutil.implicit_wait(driver, typeoftree);
	typeoftree.click();
}
public void treemenu_click(String option)
{pageutil.menu_click(driver, treemenu, option);}

public void practicemenu_click(String option)
{pageutil.menu_click(driver, practicemenu, option);}


public void tryedit_click()
{	pageutil.scrolldown(driver);
	pageutil.scrolldown(driver);
	tryhere.click();} 
public void practice_click()
{	pageutil.implicit_wait(driver, practicequestionlink);
	practicequestionlink.click();
}
public void enter_code(String code) 
{	
	WebElement e=driver.switchTo().activeElement();
	pageutil.entercode(driver, e, code);
	}
public void click_run() 
{	
	pageutil.mouse_action(driver, runbtn);
	pageutil.implicit_wait(driver,runbtn);
	runbtn.click();
}
public void click_submit()
{
	pageutil.mouse_action(driver, submitbtn);
	submitbtn.click();
}
public String present_result()
{
result=	outputbox.getText();
return result;
}
public String present_invalidmsg()
{
	result= driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	return result; 
}

}
