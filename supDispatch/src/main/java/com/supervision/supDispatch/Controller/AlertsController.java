package com.supervision.supDispatch.Controller;

import com.supervision.supDispatch.Dto.AlertRequest;
import com.supervision.supDispatch.Dto.AlertResponse;
import com.supervision.supDispatch.Service.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/alerts")
public class AlertsController {

    private static final Logger logger = LoggerFactory.getLogger(AlertsController.class);

    @Autowired
    private AlertService alertService;

    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AlertResponse> getAlertData(ServerWebExchange exchange) {
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        String platform = queryParams.getFirst("platform");
        String flow = queryParams.getFirst("flow");
        String date = queryParams.getFirst("date");

        AlertRequest request = new AlertRequest();
        if (platform != null && !platform.isEmpty()) {
            request.setPlatform(platform);
        }
        if (flow != null && !flow.isEmpty()) {
            request.setFlow(flow);
        }
        if (date != null && !date.isEmpty()) {
            try {
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                request.setDate(localDate);
            } catch (DateTimeParseException e) {
                logger.error("Invalid date format: " + date, e);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
            }
        }

        logger.debug("Fetching alert data with request: " + request);
        return alertService.getAlertData(request)
                .doOnNext(response -> logger.debug("Fetched response: " + response))
                .doOnError(e -> logger.error("Error fetching data", e));
    }
}
