package googlecalculatortest.pages.emailpages;



import googlecalculatortest.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainEmailPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='listeliens']/a[1]")
    private WebElement randomEmailGeneratorLink;
    @FindBy(xpath = "//*[@id='accept']")
    private WebElement acceptButton;

    public MainEmailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected MainEmailPage openPage() {
        driver.get(stringUtils.BASE_URL_FOR_EMAIL);
        return this;
    }

    public EmailGeneratorPage clickGenerateEmail() {
        acceptButton.click();
        randomEmailGeneratorLink.click();
        return new EmailGeneratorPage(driver);
    }
}
