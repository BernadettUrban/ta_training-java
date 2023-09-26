package packageName.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import javax.swing.text.Element;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class StartPage {
    public static final String BASE_URL = "https://pastebin.com/";
    public static final String CODE = "Hello from WebDriver";
    private WebDriver driver;


    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement pasteTextArea;

    @FindBy(xpath = "//*[@id='qc-cmp2-ui']/div[2]/div/button[2]")
    private List<WebElement> agreeButton;
//button[@class= 'css-47sehv']

    //<input type="text" id="postform-name" class="form-control" name="PostForm[name]" maxlength="100">
    @FindBy(xpath = "//*[@id='postform-name']")
    WebElement nameField;


    public StartPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }

    public StartPage openPage(){
        driver.get(BASE_URL);
        return this;
    }

    public StartPage clickAgreeButton(){
        agreeButton.get(0).click();
        return this;
    }
    public String writeInInputField(){
        pasteTextArea.sendKeys(CODE);
        return CODE;
    }



    public String selectExpiry(){

        WebElement expiryContainer = driver.findElement(By.id("select2-postform-expiration-container"));
        expiryContainer.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        List<WebElement> allOptions = driver.findElements(By.className("select2-results__options"));
        //<li class="select2-results__option" id="select2-postform-expiration-result-55wm-10M" role="option" aria-selected="false"
        // data-select2-id="select2-postform-expiration-result-55wm-10M">10 Minutes</li>
       // WebElement tenMinutes = driver.findElement(By.id("select2-postform-expiration-result-55wm-10M"));
                //.xpath("//ul[contains(@class, 'select2-results__options')]/li[3]"));
        //tenMinutes.click();



        String tenMin = "10 Minutes";

        Optional<String> answer = Optional.empty();
        for (int i = 0; i < allOptions.size(); i++) {
            
            //<li class="select2-results__option" id="select2-postform-expiration-result-oe3t-10M" role="option" aria-selected="true" data-select2-id="select2-postform-expiration-result-oe3t-10M">10 Minutes</li>
            if(allOptions.get(i).getText().contains(tenMin)){
                System.out.println(allOptions.get(i).getText().contains(tenMin));
                answer =  Optional.of(allOptions.get(i).getText());
                allOptions.get(i).click();
                        //.click();
                //expiryContainer.sendKeys(allOptions.get(i).getText());
            }
           // System.out.println(allOptions.get(i).getText());
        }


        System.out.println(answer.get().toString());
        return answer.get();
                //tenMinutes.getText();

    }

    public boolean enterPasteName(){
        nameField.sendKeys("helloweb");
        return true;
    }

}
