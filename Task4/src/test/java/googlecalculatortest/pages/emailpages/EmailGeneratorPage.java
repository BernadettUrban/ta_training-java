package googlecalculatortest.pages.emailpages;


import googlecalculatortest.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class EmailGeneratorPage extends AbstractPage {

    String BASE_URL = "https://yopmail.com/email-generator";

    public EmailGeneratorPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public EmailGeneratorPage openPage() {
        driver.get(BASE_URL);
        PageFactory.initElements(this.driver, this);
        return this;
    }

    public EmailGeneratorPage clickAgreeButton() {
        WebElement agreeButton = driver.findElement(By.xpath("//*[@id='accept']"));
        agreeButton.click();
        return this;
    }

    public EmailGeneratorPage copyEmailToClipBoard() {
        WebElement copyButton = driver.findElement(By.xpath("//*[@id='cprnd']"));
        copyButton.click();
        return this;
    }

    public EmailGeneratorPage checkInbox() {
        WebElement goToInboxButton = driver.findElement(By.xpath("/html/body/div/div[2]/main/div/div[2]/div/div[1]/div[2]/button[2]"));
        goToInboxButton.click();
        return this;
    }
}
