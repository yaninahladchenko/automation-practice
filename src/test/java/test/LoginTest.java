package test;

import org.apache.tools.ant.taskdefs.Sleep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LandingPage;

import java.io.File;

import static java.lang.Thread.sleep;

public class LoginTest {
    /**
     * Successful login test
     * 1. Navigate to https://makeup.com.ua/
     * 2. Click on "Вход в кабинет" button
     * 3. Fill login field with correct email
     * 4. Fill password field with correct password
     * 5. Click "ВОЙТИ" button
     * <p>
     * Expected Result:
     * User is signed in. Button "Вход в кабинет" is changed to "Кабинет" button
     */

    WebDriver driver;
    LandingPage landingPage;


    @BeforeMethod
    public void beforeMethod() {
        File file = new File(getClass().getClassLoader().getResource("chromedriver.exe").getFile());
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.get("https://makeup.com.ua/");
        landingPage = new LandingPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                {"yanina.hladchenko@ukr.net", "yanina123"},
                //{"Yanina.Hladchenko@ukr.net", "yanina123"},
        };
    }

    @Test(dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) throws InterruptedException {
        landingPage.login(userEmail, userPass);
        sleep(3000);
        Assert.assertTrue(landingPage.isLoaded());
    }
}


