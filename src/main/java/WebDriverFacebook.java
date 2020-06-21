import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class WebDriverFacebook {
    private WebDriver driver;

    DesiredCapabilities capabilities = DesiredCapabilities.chrome();

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\Users\\Bohdan_Mamai\\Desktop\\ONAPP\\Package2_shared_cart\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
        driver = new ChromeDriver(service, options);
    }

    @Test(description = "Just first test, JIRA binding can be here")
    public void openSortable() throws IOException {
        Fields fields = new Fields(driver);

        fields.openSortableLink().findTabs();

        WebElement draggable = driver.findElement(By.xpath(ConfigData.SECOND_ELEMENT));
        WebElement target = driver.findElement(By.xpath(ConfigData.FIVE_ELEMENT));

        new Actions(driver).dragAndDrop(draggable, target).perform();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Bohdan_Mamai\\Desktop\\Java\\HomeTask4.png"));

}

    @Test(description = "Just second test, JIRA binding can be here")
    public void openSelectable() throws IOException {
        Fields fields = new Fields(driver);
        fields.openSelectableLink().selection();

        String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date().getTime());

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Bohdan_Mamai\\Desktop\\Java\\HomeTask4sSelectable" + timestamp + ".png"));
    }

    @Test(description = "Just third test, JIRA binding can be here")
    public void openResizeable() throws IOException {
        Fields fields = new Fields(driver);

        fields.openResizableLink().getStartSize().getSecondSize().getThirdSize();
    }



    @AfterTest(alwaysRun = true)
    public void browseTearDown() {
        driver.quit();
    }

}
