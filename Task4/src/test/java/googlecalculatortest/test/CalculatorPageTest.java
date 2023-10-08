package googlecalculatortest.test;

import googlecalculatortest.model.Engine;
import googlecalculatortest.service.EngineGenerator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CalculatorPageTest extends CommonConditions {

    @BeforeClass
    public void setUp() {
        initCalculatorPageTest();
    }

    @Test(priority = 1)
    public void computeEngineSelected() {
        String actual = estimate.getComputeEngineSign();
        String expected = "Compute Engine";
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
    public void estimateIsCreatedWithCorrectInput() throws IOException {
        EngineGenerator engineGenerator = new EngineGenerator();
        Engine engine = engineGenerator.readEngineFromConfig();
        String region = stringUtils.splitEstimateLines(estimate.getRegion(), ": ", 1);

        boolean actualRegion = engine.getDatacenterLocation().contains(region);

       assertThat(Boolean.valueOf(actualRegion), equalTo(true));

        String numberOfEngines =
                estimate.getNumberOfEngines();
        boolean actualNumberOfEngines = numberOfEngines.contains(engine.getNumberOfInstances().toString());

        assertThat(Boolean.valueOf(actualNumberOfEngines), equalTo(true));

        String commitmentTerm = stringUtils.splitEstimateLines(estimate.getCommitmentTerm(), ": ", 1);

        boolean actualCommitmentTerm = engine.getCommitmentTerm().contains(commitmentTerm);

        assertThat(Boolean.valueOf(actualCommitmentTerm), equalTo(true));

        String provisioningModel = stringUtils.splitEstimateLines(estimate.getProvisioningModel(), ": ", 1);

        boolean actualProvisioningModel = engine.getProvisioningModel().contains(provisioningModel);

        assertThat(Boolean.valueOf(actualProvisioningModel), equalTo(true));


        String instanceTypeSplit1 = stringUtils.splitEstimateLines(estimate.getInstanceType(), "\n", 0);

        String instanceTypeSplit2 = stringUtils.splitEstimateLines(instanceTypeSplit1, ": ", 1);
        boolean actualInstanceType = engine.getMachineType().contains(instanceTypeSplit2);

        assertThat(Boolean.valueOf(actualInstanceType), equalTo(true));


        String operatingSystem = stringUtils.splitEstimateLines(estimate.getOperatingSystem(), ": ", 1);

        boolean actualOperatingSystem = engine.getOperatingSystem().contains(operatingSystem);

        assertThat(Boolean.valueOf(actualOperatingSystem), equalTo(true));


        String localSSD = stringUtils.splitEstimateLines(estimate.getLocalSSD(), ": ", 1);

        String splitEngineLocalSSd = stringUtils.splitEstimateLines(engine.getLocalSSD(), " ", 0);
        boolean actualLocalSSD = localSSD.contains(splitEngineLocalSSd);

        assertThat(Boolean.valueOf(actualLocalSSD), equalTo(true));

    }

    @Test(priority = 5)
    public void currencyIsCorrect() {
        String actualString = estimate.totalEstimatedCost();
        String expected = "USD";
        assertThat(Boolean.valueOf(actualString.contains(expected)), equalTo(true));
    }

    @AfterClass
    public void deleteCookies(){
        driver.manage().deleteAllCookies();
    }
}
