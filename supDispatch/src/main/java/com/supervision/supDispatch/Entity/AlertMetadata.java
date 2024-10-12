package com.supervision.supDispatch.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Document(collection = "alert_metadata")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertMetadata {

    @Id
    private String id;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("flow")
    private String flow;

    @JsonProperty("alerts")
    private List<String> alerts;

    @JsonProperty("tickets.decoded")
    private String ticketsDecoded;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<String> getAlerts() {
        return alerts;
    }

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    public void setAlerts(Object alerts) {
        if (alerts instanceof String) {
            this.alerts = Collections.singletonList((String) alerts);
        } else if (alerts instanceof List) {
            this.alerts = (List<String>) alerts;
        } else {
            throw new IllegalArgumentException("Unexpected type for alerts: " + alerts.getClass());
        }
    }

    public String getTicketsDecoded() {
        return ticketsDecoded;
    }

    public void setTicketsDecoded(String ticketsDecoded) {
        this.ticketsDecoded = ticketsDecoded;
    }
}
