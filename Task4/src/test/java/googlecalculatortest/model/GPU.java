package googlecalculatortest.model;

import java.util.Objects;

public class GPU {
    private String gpuType;
    private Integer numberOfGPUs;

    public GPU(String gpuType, Integer numberOfGPUs) {
        this.gpuType = gpuType;
        this.numberOfGPUs = numberOfGPUs;
    }

    public GPU() {
    }

    public String getGpuType() {
        return gpuType;
    }

    public void setGpuType(String gpuType) {
        this.gpuType = gpuType;
    }

    public Integer getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public void setNumberOfGPUs(Integer numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
    }

    @Override
    public String toString() {
        return "GPU{" +
                "gpuType='" + gpuType + '\'' +
                ", numberOfGPUs=" + numberOfGPUs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPU gpu = (GPU) o;
        return Objects.equals(gpuType, gpu.gpuType) && Objects.equals(numberOfGPUs, gpu.numberOfGPUs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gpuType, numberOfGPUs);
    }
}
