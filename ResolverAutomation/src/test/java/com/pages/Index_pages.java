package com.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.Driver;
import com.utilities.ReadPropertiesFile;

public class Index_pages extends Driver {
	public static final String filename = null;
	public ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public Properties prop = readPropertiesFile.readPropertiesFile(filename);

	By email_Field = By.id("inputEmail");
	By password_Field = By.id("inputPassword");
	By signIn_Button= By.xpath("//button[contains(text(),'Sign in')]");
	By list_Items = By.xpath("//*[@id=\"test-2-div\"]/ul/li");
	By dropdown_Field = By.id("dropdownMenuButton");
	By option2_Value= By.xpath("//a[contains(text(),'Option 2')]");
	By button_one = By.xpath("(//button[contains(text(),'Button')])[1]");
	By button_two = By.xpath("(//button[contains(text(),'Button')])[2]");
	By button_three = By.xpath("(//button[contains(text(),'Button')])[3]");
	By alert_popup = By.id("test5-alert");
	By table_xpath = By.xpath("//table/tbody");
	
	public Index_pages() {
		PageFactory.initElements(driver, this);
	}

	public void navigateTo_IndexUI() {
		driver.get(System.getProperty("user.dir") + prop.getProperty("URL"));
	}

	public boolean username_field() {
		WebElement element = driver.findElement(email_Field);
		return element.isDisplayed();
	}

	public boolean password_field() {
		WebElement element = driver.findElement(password_Field);
		return element.isDisplayed();
	}

	public void enter_Username() {
		WebElement element = driver.findElement(email_Field);
		element.sendKeys("Test1");
	}

	public void enter_password() {
		WebElement element = driver.findElement(password_Field);
		element.sendKeys("Test1");
	}

	public boolean login_button() {
		WebElement element = driver.findElement(signIn_Button);
		return element.isDisplayed();
	}

	public int no_of_list_Items() {
		List<WebElement> element = driver.findElements(list_Items);
		return element.size();
	}

	public String second_List_Value() {
		List<WebElement> element = driver.findElements(list_Items);
		String li_value = element.get(1).getText();
		String[] arr = li_value.split("6");
		return arr[0];
	}

	public String badge_Value() {
		List<WebElement> element = driver.findElements(list_Items);
		String li_value = element.get(1).getText();
		String val =li_value.replace("List Item 2 6", "6");
		return val;
	}

	public String default_Selected_value() {
		WebElement element = driver.findElement(dropdown_Field);
		return element.getText();
	}

	public void select_Option_Two() {
		driver.findElement(dropdown_Field).click();
		WebElement element = driver.findElement(option2_Value);
		element.click();
	}

	public boolean button_one_enabled() {
		WebElement element = driver.findElement(button_one);
		return element.isEnabled();
	}

	public boolean button_two_disabled() {
		WebElement element = driver.findElement(button_two);
		return element.isEnabled();
	}

	public String alert_Text_Message() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(button_three));
		WebElement button = driver.findElement(button_three);
		if (button.isDisplayed()) {
			button.click();
		}
		WebElement element = driver.findElement(alert_popup);
		return element.getText();
	}

	public boolean button_disabled() {
		WebElement button = driver.findElement(button_three);
		return button.isEnabled();
	}

	public String get_Value_from_Table() {
		WebElement webtable = driver.findElement(table_xpath);
		String celltext = null;
		List<WebElement> rows_table = webtable.findElements(By.tagName("tr"));
		int rows_count = rows_table.size();
		for (int row = 0; row < rows_count; row++) {
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			int columns_count = Columns_row.size();
			for (int column = 0; column < columns_count; column++) {
				celltext = Columns_row.get(2).getText();
			}
		}
		return celltext;
	}
}