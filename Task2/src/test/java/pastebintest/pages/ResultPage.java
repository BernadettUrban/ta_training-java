package pastebintest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResultPage {
    private final WebDriver driver;


    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//a[@class = 'btn -small h_800']")
    private WebElement syntax;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[1]/div[4]/div[2]/ol")
    private  List<WebElement> code;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public String getTitle(){
        return title.getText();
    }

    public String getSyntax(){
        return syntax.getText();
    }

    public String getCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < code.size(); i++) {
            sb.append(code.get(i).getText());
        }
        return sb.toString();
    }
}
