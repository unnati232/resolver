package com.tests;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.driver.Driver;
import com.pages.Index_pages;
import com.utilities.ReadPropertiesFile;

public class IndexTest extends Driver {

	public static final String filename = null;
	public Index_pages index_page = new Index_pages();
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);

	@BeforeClass
	public void initialization() {
		Driver.init(prop.getProperty("Browser"));
		index_page.navigateTo_IndexUI();
		Assert.assertEquals(Driver.driver.getTitle(), "Home");
	}

	@Test(priority = 0)
	public void index_Test1() {
		boolean flag_Username = index_page.username_field();
		boolean flag_Password = index_page.password_field();
		boolean flag_Login_Button = index_page.password_field();
		Assert.assertEquals(flag_Username, true);
		Assert.assertEquals(flag_Password, true);
		Assert.assertEquals(flag_Login_Button, true);
		index_page.enter_Username();
		index_page.enter_password();
	}

	@Test(priority = 1)
	public void index_Test2() {
		int list_values = index_page.no_of_list_Items();
		Assert.assertEquals(list_values, 3);
		String second_li_Value = index_page.second_List_Value();
		Assert.assertEquals(second_li_Value.trim(), "List Item 2");
		String badge_value = index_page.badge_Value();
		Assert.assertEquals(badge_value, "6");
	}

	@Test(priority = 2)
	public void index_Test3() {
		String default_value = index_page.default_Selected_value();
		Assert.assertEquals(default_value, "Option 1");
		index_page.select_Option_Two();
		String new_default_value = index_page.default_Selected_value();
		Assert.assertEquals(new_default_value, "Option 2");
	}

	@Test(priority = 3)
	public void index_Test4() {
		boolean enabled_button = index_page.button_one_enabled();
		Assert.assertEquals(enabled_button, true);
		boolean disabled_button = index_page.button_two_disabled();
		Assert.assertEquals(disabled_button, false);
	}

	@Test(priority = 4)
	public void index_Test5() {
		String alert_message = index_page.alert_Text_Message();
		Assert.assertEquals(alert_message, "You clicked a button!");
		boolean button_disabled = index_page.button_disabled();
		Assert.assertEquals(button_disabled, false);
	}

	@Test(priority = 5)
	public void index_Test6() {
		String actual_Text = index_page.get_Value_from_Table();
		Assert.assertEquals(actual_Text, "Ventosanzap");
	}

	@AfterTest
	public void quit() {
		Driver.driver.quit();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}