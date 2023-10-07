package googlecalculatortest.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

public class PricingCalculatorPageConfiguration {
    private Integer numberOfInstances;
    private String operatingSystem;
    private String provisioningModel;
    private String machineFamily;
    private String series;
    private String machineType;
    private boolean addGPUs;
    private String GPUoption;
    private Integer numberOfGPUs;
    private String localSSD;
    private String datacenterLocation;
    private String commitmentTerm;

    public PricingCalculatorPageConfiguration(Integer numberOfInstances, String operatingSystem, String provisioningModel, String machineFamily, String series, String machineType, boolean addGPUs, String GPUoption, Integer numberOfGPUs, String localSSD, String datacenterLocation, String commitmentTerm) {
        this.numberOfInstances = numberOfInstances;
        this.operatingSystem = operatingSystem;
        this.provisioningModel = provisioningModel;
        this.machineFamily = machineFamily;
        this.series = series;
        this.machineType = machineType;
        this.addGPUs = addGPUs;
        this.GPUoption = GPUoption;
        this.numberOfGPUs = numberOfGPUs;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
        this.commitmentTerm = commitmentTerm;
    }

    public Integer getNumberOfInstances() {
        return numberOfInstances;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getProvisioningModel() {
        return provisioningModel;
    }

    public String getMachineFamily() {
        return machineFamily;
    }

    public String getSeries() {
        return series;
    }

    public String getMachineType() {
        return machineType;
    }

    public boolean isAddGPUs() {
        return addGPUs;
    }

    public String getGPUoption() {
        return GPUoption;
    }

    public Integer getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public String getCommitmentTerm() {
        return commitmentTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricingCalculatorPageConfiguration that = (PricingCalculatorPageConfiguration) o;
        return addGPUs == that.addGPUs && Objects.equals(numberOfInstances, that.numberOfInstances) && Objects.equals(operatingSystem, that.operatingSystem) && Objects.equals(provisioningModel, that.provisioningModel) && Objects.equals(machineFamily, that.machineFamily) && Objects.equals(series, that.series) && Objects.equals(machineType, that.machineType) && Objects.equals(GPUoption, that.GPUoption) && Objects.equals(numberOfGPUs, that.numberOfGPUs) && Objects.equals(localSSD, that.localSSD) && Objects.equals(datacenterLocation, that.datacenterLocation) && Objects.equals(commitmentTerm, that.commitmentTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, operatingSystem, provisioningModel, machineFamily, series, machineType, addGPUs, GPUoption, numberOfGPUs, localSSD, datacenterLocation, commitmentTerm);
    }

    @Override
    public String toString() {
        return "PricingCalculatorPageConfiguration{" +
                "numberOfInstances=" + numberOfInstances +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", provisioningModel='" + provisioningModel + '\'' +
                ", machineFamily='" + machineFamily + '\'' +
                ", series='" + series + '\'' +
                ", machineType='" + machineType + '\'' +
                ", addGPUs=" + addGPUs +
                ", GPUoption='" + GPUoption + '\'' +
                ", numberOfGPUs=" + numberOfGPUs +
                ", localSSD='" + localSSD + '\'' +
                ", datacenterLocation='" + datacenterLocation + '\'' +
                ", commitmentTerm='" + commitmentTerm + '\'' +
                '}';
    }
}
