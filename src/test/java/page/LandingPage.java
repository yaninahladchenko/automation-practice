package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LandingPage {
    protected WebDriver driver;

    @FindBy(xpath = "//div[@data-popup-handler='auth' and not(@class)]")
    private WebElement enterMyAccountButton;

    @FindBy(xpath = "//*[@name='user_login']")
    private WebElement userLoginField;

    @FindBy(xpath = "//*[@name='user_pw']")
    private WebElement userPassField;

    @FindBy(xpath = "//h2[text()='вход в личный кабинет']/..//button")
    private WebElement confirmButton;

    @FindBy(xpath = "//a[contains(text(),'Кабинет')]")
    private WebElement myAccountButton;

    @FindBy(xpath = "//a[@class='menu-column-list__link' and contains(text(),'Новинки')]")
    private WebElement perfumerySubcategoryMenuItem;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LandingPage login(String userEmail, String userPass) {
        enterMyAccountButton.click();
        userLoginField.sendKeys(userEmail);
        userPassField.sendKeys(userPass);
        confirmButton.click();
        return new LandingPage(driver);
    }

    public boolean isLoaded() {
        return myAccountButton.isDisplayed();
    }

    public void hoverOverCategoryMenuItem(String categoryName) {
        WebElement categoryMenuItem = driver.findElement(
                By.xpath(String.format("//a[@class='menu-list__link'][contains(text(),'%s')]", categoryName)));
        Actions builder = new Actions(driver);
        builder.moveToElement(categoryMenuItem).perform();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public SubcategoriesPage clickPerfumerySubcategoryMenuItem(){
        perfumerySubcategoryMenuItem.click();
        return new SubcategoriesPage(driver);
    }
}
