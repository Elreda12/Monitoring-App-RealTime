package com.supervision.supDispatch.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class AlertRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String platform;
    private String flow;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }
}
