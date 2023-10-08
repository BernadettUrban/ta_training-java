package googlecalculatortest.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchResultPageTest extends CommonConditions {

    @BeforeClass
    public void setUp() {
        initSearchResultTest();
    }

    @Test
    public void clickOnFirstLink() {
        String url = searchResultPage.clickLink();
        String expected = "https://cloud.google.com/products/calculator";
        assertThat(url, equalTo(expected));
    }
}
