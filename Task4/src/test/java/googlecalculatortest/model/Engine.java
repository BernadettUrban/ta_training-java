package googlecalculatortest.model;

import java.util.Objects;

public class Engine {
    private Integer numberOfInstances;
    private String operatingSystem;
    private String provisioningModel;
    private String machineFamily;
    private String series;
    private String machineType;
    private GPU addGPUs;
    private String localSSD;
    private String datacenterLocation;
    private String commitmentTerm;

    public Engine(Integer numberOfInstances, String operatingSystem, String provisioningModel, String machineFamily, String series, String machineType, GPU addGPUs, String localSSD, String datacenterLocation, String commitmentTerm) {
        this.numberOfInstances = numberOfInstances;
        this.operatingSystem = operatingSystem;
        this.provisioningModel = provisioningModel;
        this.machineFamily = machineFamily;
        this.series = series;
        this.machineType = machineType;
        this.addGPUs = addGPUs;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
        this.commitmentTerm = commitmentTerm;
    }

    public Engine() {
    }

    public Integer getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(Integer numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getProvisioningModel() {
        return provisioningModel;
    }

    public void setProvisioningModel(String provisioningModel) {
        this.provisioningModel = provisioningModel;
    }

    public String getMachineFamily() {
        return machineFamily;
    }

    public void setMachineFamily(String machineFamily) {
        this.machineFamily = machineFamily;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public GPU getAddGPUs() {
        return addGPUs;
    }

    public void setAddGPUs(GPU addGPUs) {
        this.addGPUs = addGPUs;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public void setDatacenterLocation(String datacenterLocation) {
        this.datacenterLocation = datacenterLocation;
    }

    public String getCommitmentTerm() {
        return commitmentTerm;
    }

    public void setCommitmentTerm(String commitmentTerm) {
        this.commitmentTerm = commitmentTerm;
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
                ", localSSD='" + localSSD + '\'' +
                ", datacenterLocation='" + datacenterLocation + '\'' +
                ", commitmentTerm='" + commitmentTerm + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine that = (Engine) o;
        return Objects.equals(numberOfInstances, that.numberOfInstances) && Objects.equals(operatingSystem, that.operatingSystem) && Objects.equals(provisioningModel, that.provisioningModel) && Objects.equals(machineFamily, that.machineFamily) && Objects.equals(series, that.series) && Objects.equals(machineType, that.machineType) && Objects.equals(addGPUs, that.addGPUs) && Objects.equals(localSSD, that.localSSD) && Objects.equals(datacenterLocation, that.datacenterLocation) && Objects.equals(commitmentTerm, that.commitmentTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, operatingSystem, provisioningModel, machineFamily, series, machineType, addGPUs, localSSD, datacenterLocation, commitmentTerm);
    }
}
