package calculatortest.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StartPageTest extends CommonTestConditions {
    @BeforeTest()
    public void setUp() {
        initStartPage();
    }

    @Test
    public void search() {

        searchResultPage = startPage.performSearch(stringUtils.SEARCH_TERM);
        String actual = searchResultPage.getTextFromInput();
        assertThat(actual, equalTo(stringUtils.SEARCH_TERM));

    }

}
