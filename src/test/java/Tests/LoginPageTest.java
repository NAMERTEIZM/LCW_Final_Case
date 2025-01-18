package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

@Epic("User Authentication")
@Feature("Login Functionality")
public class LoginPageTest extends BaseTest {

    final String loginURL = "https://www.lcw.com/giris";

    @Test
    @Story("Valid Login Test")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify user can successfully log in with valid credentials.")
    @Step("Performing login test with valid credentials.")
    public void login() {
        try {
            System.out.println("Driver is: " + (driver == null ? "null" : "initialized"));
            LoginPage loginPage = new LoginPage(driver);

            // Navigate to login page and hover-click the login button
            loginPage.hoverAndClickLogin();
            Allure.step("Navigated to login page and clicked the login button.");

            // Perform login action
            HomePage homePage = loginPage.login("musabeseoglum@gmail.com", "Qwerty.12345");
            Allure.step("Entered valid credentials and logged in.");

            // Verify login success
            //Assert.assertEquals(homePage.getAccountUserText(), "Hesabım", "Login failed or account text mismatch");
           // Allure.step("Verified account text: Login successful.");
        } catch (Exception e) {
            Allure.addAttachment("Login Test Error", e.getMessage());
            throw e; // Re-throw exception to fail the test in case of error
        }
    }
}
