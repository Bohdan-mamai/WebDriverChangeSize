import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Fields {
    private WebDriver driver;
    private WebDriverWait wait;

    public Fields(WebDriver driver){
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 5);
    }

    public Fields openSortableLink(){
       driver.get(ConfigData.LINK_SORTABLE);
        return this;
    }

    public Fields findTabs(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ConfigData.SECOND_ELEMENT)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ConfigData.FIVE_ELEMENT)));
        return this;
    }

    public Fields openSelectableLink(){
        driver.get(ConfigData.LINK_SELECTABLE);
        return this;
    }

    public Fields selection(){
        Random rand = new Random();
        List<WebElement> links;
        links = driver.findElements(By.xpath(ConfigData.LINK_LI_SELECTABLE));
        for (int i = 0; i < 3; i++) {
            int randomly = rand.nextInt(links.size());
            links.get(randomly).click();
            links.remove(randomly);
        }
        return this;
    }

    public Fields openResizableLink(){
        driver.get(ConfigData.LINK_RESIZABLE);
        return this;
    }

    public Fields getStartSize() throws IOException {
        WebElement size = driver.findElement(By.xpath(ConfigData.SIZE_OF_ELEMENT));
        String newSize = size.getAttribute("style");
        System.out.println("Start size " + newSize);

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Bohdan_Mamai\\Desktop\\Java\\HomeTask4StartSize.png"));
        return this;
    }

    public Fields getSecondSize() throws IOException {
        Actions actions = new Actions(driver);

        WebElement sizeElement = driver.findElement(By.xpath(ConfigData.RESIZABLE_ELEMENT));

        actions.clickAndHold(sizeElement).moveByOffset(100,80).perform();

        WebElement size = driver.findElement(By.xpath(ConfigData.SIZE_OF_ELEMENT));

        System.out.println("Second size " + size.getAttribute("style"));

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Bohdan_Mamai\\Desktop\\Java\\HomeTask4SecondSize.png"));
        return this;
    }

    public Fields getThirdSize() throws IOException {
        Actions actions = new Actions(driver);
        WebElement sizeElement = driver.findElement(By.xpath(ConfigData.RESIZABLE_ELEMENT));

        actions.clickAndHold(sizeElement).moveByOffset(-170,-170).perform();

        WebElement size = driver.findElement(By.xpath(ConfigData.SIZE_OF_ELEMENT));

        System.out.println("Third size " + size.getAttribute("style"));

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Bohdan_Mamai\\Desktop\\Java\\HomeTask4ThirdSize.png"));
        return this;
    }




}
