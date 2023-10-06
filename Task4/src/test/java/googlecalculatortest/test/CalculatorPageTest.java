package googlecalculatortest.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CalculatorPageTest extends CommonTestConditions {

    @BeforeTest()
    public void setUp() {
        initCalculatorPageTest();
    }

    @Test(priority = 1)
    public void computeEngineSelected() {
        String actual = estimate.getComputeEngineSign();
        String expected = "Compute Engine1";
        assertThat(actual, equalTo(expected));
    }

    @Test(priority = 2)
    public void monthlyCostIsCorrect() {
        String actualString = estimate.getMonthlyEstimate();
        String actual = stringUtils.regexForUSD(actualString);
        String expected = stringUtils.regexForUSD(estimate.totalEstimatedCost());
        assertThat(actual, equalTo(expected));
    }

    @Test(priority = 3)
    public void estimatedCostIsCalculatedCorrectly() {
        String actual = estimate.totalEstimatedCost();
        String expected = "Total Estimated Cost: USD 1,081.20 per 1 month";
        assertThat(actual, equalTo(expected));
    }

    @Test(priority = 4)
    public void regionIsCorrectInEstimate() {
        String actualString = estimate.getRegion();
        boolean actual = actualString.contains(stringUtils.REGION);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }

    @Test(priority = 5)
    public void numberOfEnginesIsCorrect() {
        String actualString = estimate.getNumberOfEngines();
        boolean actual = actualString.contains(stringUtils.NUMBER_OF_INSTANCES);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }

    @Test(priority = 6)
    public void commitmentTermIsCorrect() {
        String actualString = estimate.getCommitmentTerm();
        boolean actual = actualString.contains(stringUtils.COMMITMENT_TERM);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }


    @Test(priority = 7)
    public void getProvisioningModel() {
        String actualString = estimate.getProvisioningModel();
        boolean actual = actualString.contains(stringUtils.PROVISIONING_MODEL);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }


    @Test(priority = 8)
    public void instanceTypeIsCorrect() {
        String actualString = estimate.getInstanceType();
        boolean actual = actualString.contains(stringUtils.INSTANCE_TYPE);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }

    @Test(priority = 9)
    public void operatingSystemIsCorrect() {
        String actualString = estimate.getOperatingSystem();
        boolean actual = actualString.contains(stringUtils.OPERATING_SYSTEM);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }

    @Test(priority = 10)
    public void localSSDIsCorrect() {
        String actualString = estimate.getLocalSSD();
        boolean actual = actualString.contains(stringUtils.LOCAL_SSD);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }

    @Test(priority = 11)
    public void currencyIsCorrect() {
        String actualString = estimate.totalEstimatedCost();
        String expected = "USD";
        assertThat(Boolean.valueOf(actualString.contains(expected)), equalTo(true));
    }

}
