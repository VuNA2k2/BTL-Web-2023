package com.example.webs2023.dto.rate;

public class AvgRateOutput {
    private Double avgRate;
    private Long countRate;

    public AvgRateOutput() {
    }

    public Double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(Double avgRate) {
        this.avgRate = avgRate;
    }

    public Long getCountRate() {
        return countRate;
    }

    public void setCountRate(Long countRate) {
        this.countRate = countRate;
    }

    public AvgRateOutput(Double avgRate, Long countRate) {
        this.avgRate = avgRate;
        this.countRate = countRate;
    }
}
