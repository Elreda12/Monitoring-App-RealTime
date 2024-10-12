package com.supervision.supDispatch.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "file_metadata")
public class FileMetadata {

    @Id
    private String id;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("flow")
    private String flow;

    @JsonProperty("tickets.decoded")
    private String ticketsDecoded;

    @JsonProperty("tickets.sent.M6")
    private String ticketsSentM6;

    @JsonProperty("tickets.sent.M7")
    private String ticketsSentM7;

    @JsonProperty("file.size.transferred.M6")
    private String fileSizeTransferredM6;

    @JsonProperty("file.size.transferred.M7")
    private String fileSizeTransferredM7;

    // Getters and Setters

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

    public String getTicketsDecoded() {
        return ticketsDecoded;
    }

    public void setTicketsDecoded(String ticketsDecoded) {
        this.ticketsDecoded = ticketsDecoded;
    }

    public String getTicketsSentM6() {
        return ticketsSentM6;
    }

    public void setTicketsSentM6(String ticketsSentM6) {
        this.ticketsSentM6 = ticketsSentM6;
    }

    public String getTicketsSentM7() {
        return ticketsSentM7;
    }

    public void setTicketsSentM7(String ticketsSentM7) {
        this.ticketsSentM7 = ticketsSentM7;
    }

    public String getFileSizeTransferredM6() {
        return fileSizeTransferredM6;
    }

    public void setFileSizeTransferredM6(String fileSizeTransferredM6) {
        this.fileSizeTransferredM6 = fileSizeTransferredM6;
    }

    public String getFileSizeTransferredM7() {
        return fileSizeTransferredM7;
    }

    public void setFileSizeTransferredM7(String fileSizeTransferredM7) {
        this.fileSizeTransferredM7 = fileSizeTransferredM7;
    }
}
