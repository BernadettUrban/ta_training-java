package googlecalculatortest.test;

import googlecalculatortest.model.Engine;
import googlecalculatortest.service.EngineGenerator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

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
        String region = estimate.getRegion();
        boolean actualRegion = region.contains(engine.getDatacenterLocation());
                //stringUtils.REGION);
        assertThat(Boolean.valueOf(actualRegion), equalTo(true));

        String umberOfEngines = estimate.getNumberOfEngines();
        boolean actualNumberOfEngines = umberOfEngines.contains(engine.getNumberOfInstances().toString());
                //stringUtils.NUMBER_OF_INSTANCES);
        assertThat(Boolean.valueOf(actualNumberOfEngines), equalTo(true));

        String commitmentTerm = estimate.getCommitmentTerm();
        boolean actualCommitmentTerm = commitmentTerm.contains(engine.getCommitmentTerm());
                //stringUtils.COMMITMENT_TERM);
        assertThat(Boolean.valueOf(actualCommitmentTerm), equalTo(true));

        String provisioningModel = estimate.getProvisioningModel();
        boolean actualProvisioningModel = provisioningModel.contains(engine.getProvisioningModel());
                //stringUtils.PROVISIONING_MODEL);
        assertThat(Boolean.valueOf(actualProvisioningModel), equalTo(true));


        String instanceType = estimate.getInstanceType();
        boolean actualInstanceType = instanceType.contains(engine.getMachineType());
                //stringUtils.INSTANCE_TYPE);
        assertThat(Boolean.valueOf(actualInstanceType), equalTo(true));


        String operatingSystem = estimate.getOperatingSystem();
        boolean actualOperatingSystem = operatingSystem.contains(engine.getOperatingSystem());
                //stringUtils.OPERATING_SYSTEM);
        assertThat(Boolean.valueOf(actualOperatingSystem), equalTo(true));


        String localSSD = estimate.getLocalSSD();
        boolean actualLocalSSD = localSSD.contains(engine.getLocalSSD());
                //stringUtils.LOCAL_SSD);
        assertThat(Boolean.valueOf(actualLocalSSD), equalTo(true));

    }

    @Test(priority = 5)
    public void currencyIsCorrect() {
        String actualString = estimate.totalEstimatedCost();
        String expected = "USD";
        assertThat(Boolean.valueOf(actualString.contains(expected)), equalTo(true));
    }

}
