package pastebintest.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pastebintest.driver.DriverSingleton;
import pastebintest.pages.ResultPage;
import pastebintest.pages.StartPage;
import pastebintest.util.StringUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResultPageTest {
    protected WebDriver driver;
    StartPage startPage;
    ResultPage resultPage;

    public final StringUtils stringUtils = new StringUtils();

    String url = "https://pastebin.com/mL4bNXwC";

    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();
        startPage = new StartPage(driver);

        resultPage =startPage
                .writeCodeInInputField()
                .toogleSyntaxSwitch()
                .selectSyntax()
                .selectExpiry()
                .enterPasteName()
                .savePaste();

        driver.get(startPage.getNewUrl());
    }

    @Test
    public void pasteNameEqualsToGivenTitle(){
        String name = resultPage.getTitle();
        assertThat(name, equalTo(stringUtils.CODE));
    }

    @Test
    public void syntaxIsBash(){
        String syntax = resultPage.getSyntax();
        assertThat(syntax, equalTo(stringUtils.SYNTAX));
    }
    @Test
    public void codeIsTheSame(){
        String code = resultPage.getCode();
        assertThat(code, equalTo(stringUtils.CODE));
    }

}
