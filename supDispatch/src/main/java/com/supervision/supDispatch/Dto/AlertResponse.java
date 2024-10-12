package com.supervision.supDispatch.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class AlertResponse {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("flow")
    private String flow;

    @JsonProperty("alerts")
    private List<String> alerts;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<String> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<String> alerts) {
        this.alerts = alerts;
    }
}
