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
        String region = stringUtils.splitEstimateLines(estimate.getRegion(), ": ", 1);

        System.out.println(region);
        boolean actualRegion = engine.getDatacenterLocation().contains(region);
                //region.contains(engine.getDatacenterLocation());
                //stringUtils.REGION);
        System.out.println(engine.getDatacenterLocation());
        System.out.println(engine.getDatacenterLocation().contains(region));
       assertThat(Boolean.valueOf(engine.getDatacenterLocation().contains(region)), equalTo(true));

        String numberOfEngines =
                estimate.getNumberOfEngines();
        boolean actualNumberOfEngines = numberOfEngines.contains(engine.getNumberOfInstances().toString());
                //stringUtils.NUMBER_OF_INSTANCES);
        assertThat(Boolean.valueOf(actualNumberOfEngines), equalTo(true));

        String commitmentTerm = stringUtils.splitEstimateLines(estimate.getCommitmentTerm(), ": ", 1);
                //estimate.getCommitmentTerm();
        boolean actualCommitmentTerm = engine.getCommitmentTerm().contains(commitmentTerm);
                //commitmentTerm.contains(engine.getCommitmentTerm());
                //stringUtils.COMMITMENT_TERM);
        System.out.println("commintment");
        System.out.println(commitmentTerm);
        System.out.println(engine.getCommitmentTerm());
        assertThat(Boolean.valueOf(actualCommitmentTerm), equalTo(true));

        String provisioningModel = stringUtils.splitEstimateLines(estimate.getProvisioningModel(), ": ", 1);
                //estimate.getProvisioningModel();
        boolean actualProvisioningModel = engine.getProvisioningModel().contains(provisioningModel);
                //provisioningModel.contains(engine.getProvisioningModel());
                //stringUtils.PROVISIONING_MODEL);
        System.out.println("provision");
        System.out.println(provisioningModel);
        System.out.println(engine.getProvisioningModel());
        assertThat(Boolean.valueOf(actualProvisioningModel), equalTo(true));


        String instanceTypeSplit1 = stringUtils.splitEstimateLines(estimate.getInstanceType(), "\n", 0);
                //estimate.getInstanceType();
        String instanceTypeSplit2 = stringUtils.splitEstimateLines(instanceTypeSplit1, ": ", 1);
        boolean actualInstanceType = engine.getMachineType().contains(instanceTypeSplit2);
                //instanceType.contains(engine.getMachineType());
                //stringUtils.INSTANCE_TYPE);
        System.out.println(instanceTypeSplit2);
        System.out.println(engine.getMachineType());
        assertThat(Boolean.valueOf(actualInstanceType), equalTo(true));


        String operatingSystem = stringUtils.splitEstimateLines(estimate.getOperatingSystem(), ": ", 1);
                //estimate.getOperatingSystem();
        boolean actualOperatingSystem = engine.getOperatingSystem().contains(operatingSystem);
                //operatingSystem.contains(engine.getOperatingSystem());
                //stringUtils.OPERATING_SYSTEM);
        System.out.println(operatingSystem);
        System.out.println(engine.getOperatingSystem());
        assertThat(Boolean.valueOf(actualOperatingSystem), equalTo(true));


        String localSSD = stringUtils.splitEstimateLines(estimate.getLocalSSD(), ": ", 1);
                //estimate.getLocalSSD();
        boolean actualLocalSSD = localSSD.contains(engine.getLocalSSD());
                //stringUtils.LOCAL_SSD);
        System.out.println(localSSD);
        System.out.println(actualLocalSSD);
        assertThat(Boolean.valueOf(actualLocalSSD), equalTo(true));

    }

    @Test(priority = 5)
    public void currencyIsCorrect() {
        String actualString = estimate.totalEstimatedCost();
        String expected = "USD";
        assertThat(Boolean.valueOf(actualString.contains(expected)), equalTo(true));
    }

}
