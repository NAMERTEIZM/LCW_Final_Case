package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends BasePage {

    private final By loginHoverButtonLocator = By.id("user_1_"); // Hover // üstüne mause getirilip alt sekmedeki giriş yap butonu açılacak
    private final By loginButtonLocator = By.xpath("//a[@class='cart-action__btn cart-action__btn--bg-blue']"); // Tıklanacak giriş yap butonu
    private By emailInputBy = By.xpath("//*[@id=\"login\"]/div/div[2]/div/div/div/form/div/div/input");
    private By passwordInputBy = By.xpath("//*[@id=\"login\"]/div/div[2]/div/div/div/form/div[2]/div/input");
    private By loginButtonBy = By.xpath("//*[@id=\"login\"]/div/div[2]/div/div/div/form/button");
    private By continueButtonBy = By.xpath("//*[@id=\"login\"]/div/div[2]/div/div/div/form/div/button");


    public LoginPage(WebDriver driver) {
        super(driver);

    }

    public WebElement loginButton() {
        return driver.findElement(loginButtonBy);

    }

    public WebElement continueButton() {
        return driver.findElement(continueButtonBy);

    }

    public WebElement emailInput() {

        return driver.findElement(emailInputBy);
    }

    public WebElement passwordInput() {

        return driver.findElement(passwordInputBy);
    }

    public void enterPassword(String text) {
        passwordInput().sendKeys(text);
    }

    public void enterEmail(String text) {
        emailInput().sendKeys(text);
    }

    public void clickLoginButton() {
        loginButton().click();
    }

    public void clickContinueButton() {
        continueButton().click();
    }

    public HomePage login(String email, String password) {
        enterEmail(email);
        clickContinueButton();
        enterPassword(password);
        clickLoginButton();
        return new HomePage(driver);
    }

    private boolean isContinueButtonVisible() {
        try {
            return continueButton().isDisplayed();
        } catch (Exception e) {
            System.out.printf("Continue button is invisible");
            return false;
        }


    }
    public void hoverAndClickLogin() {
        // Hover ve tıklama işlemleri için Actions sınıfı kullanılır
        Actions actions = new Actions(driver);
        // Hover yapılacak olan login butonu
        WebElement hoverButton = driver.findElement(loginHoverButtonLocator);
        // Fareyi butonun üzerine sürükle
        actions.moveToElement(hoverButton).perform();
        // 1 saniye bekle
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Çıkan 'Giriş Yap' butonuna tıkla
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }


}
