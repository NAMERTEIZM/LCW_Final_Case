package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class LoginPageTest extends BaseTest{

    final String loginURL ="https://www.lcw.com/giris";
    @Test
    public void login()
    {

        System.out.println("Driver is: " + (driver == null ? "null" : "initialized"));
        //driver.navigate().to(loginURL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.hoverAndClickLogin();
        HomePage homePage = loginPage.login("musabeseoglum@gmail.com","Qwerty.12345");
        Assert.assertEquals(homePage.getAccountUserText(),"Hesabım","Login failed or account text mismatch");

    }


}
