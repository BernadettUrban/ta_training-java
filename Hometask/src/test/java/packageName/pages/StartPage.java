package packageName.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class StartPage {
    public static final String BASE_URL = "https://pastebin.com/";
    public static final String CODE = "Hello from WebDriver";
    //<input type="text" id="postform-name" class="form-control" name="PostForm[name]" maxlength="100">
    @FindBy(xpath = "//*[@id='postform-name']")
    WebElement nameField;
    private final WebDriver driver;
    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement pasteTextArea;
//button[@class= 'css-47sehv']
    @FindBy(xpath = "//*[@id='qc-cmp2-ui']/div[2]/div/button[2]")
    private List<WebElement> agreeButton;


    public StartPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }

    public StartPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public StartPage clickAgreeButton() {
        agreeButton.get(0).click();
        return this;
    }

    public String writeInInputField() {
        pasteTextArea.sendKeys(CODE);
        return CODE;
    }


    public String selectExpiry() {

        WebElement expiryContainer = driver.findElement(By.id("select2-postform-expiration-container"));
        expiryContainer.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        List<WebElement> allOptions = driver.findElements(By.className("select2-results__options"));

        String tenMin = "10 Minutes";

        Optional<String> answer = Optional.empty();
        for (int i = 0; i < allOptions.size(); i++) {

            if (allOptions.get(i).getText().contains(tenMin)) {

                answer = Optional.of(allOptions.get(i).getText());
                allOptions.get(i).click();

            }

        }

        return answer.get();

    }

    public boolean enterPasteName() {
        nameField.sendKeys("helloweb");
        return true;
    }

}
