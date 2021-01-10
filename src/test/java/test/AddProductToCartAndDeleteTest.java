package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LandingPage;

import static java.lang.Thread.sleep;

public class AddProductToCartAndDeleteTest {

    WebDriver driver;
    LandingPage landingPage;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yanina_Hladchenko\\IdeaProjects\\automation-practice\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://makeup.com.ua/");
        landingPage = new LandingPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    /** AAd product to cart and delete it test:
     * 1. Navigate to https://makeup.com.ua/
     * 2. Click on "Вход в кабинет" button
     * 3. Fill login field with correct email
     * 4. Fill password field with correct password
     * 5. Click "ВОЙТИ" button
     * 6. Hover over the "Парфюмерия" menu item
     * 7. Click "Новинки" submenu item under "Парфюмерия" menu
     * 8. Click "КУПИТЬ" button
     * 9. Click close icon on cart popup window
     * 10. Verify product quantity is "1" in a shopping cart
     * 11. Click "Корзина" button
     * 12. Click decrease button in cart popup
     * 13. Click close icon on cart popup window
     * 14. Verify product quantity is "0" in a shopping cart
     */
    @Test
    public void addProductToCartAndDeleteTest() throws InterruptedException {
        landingPage.login("yanina.hladchenko@ukr.net", "yanina123");
        sleep(3000);
        Assert.assertTrue(landingPage.isLoaded());
        landingPage.hoverOverCategoryMenuItem("Парфюмерия");
        landingPage.clickPerfumerySubcategoryMenuItem();
    }
}
