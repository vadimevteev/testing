package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.BiletixHomePage;

public class WebDriverBiletixTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp(){
       ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080",
                "--disable-extensions", "--proxy-server='direct://'", "--proxy-bypass-list=*", "--start-maximized",
                "--disable-gpu", "--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    @Test
    public void findTicketsWithSameDepartureAndArrivalTest() {
        String destinationPoint = "Minsk";
        String errorMessageExpected = "Упс! А билетов и нет";

        BiletixHomePage homePage = new BiletixHomePage(driver)
                .openPage()
                .fillArrivalForm(destinationPoint)
                .fillDepartureForm(destinationPoint)
                .pressFindButton();

        String errorMessageActual = homePage.getErrorMessageText();
        Assert.assertEquals(errorMessageExpected,errorMessageActual);

    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown(){
        driver.quit();
    }

}
