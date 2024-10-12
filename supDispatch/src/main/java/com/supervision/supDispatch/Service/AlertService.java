package com.supervision.supDispatch.Service;

import com.supervision.supDispatch.Dto.AlertRequest;
import com.supervision.supDispatch.Dto.AlertResponse;
import com.supervision.supDispatch.Entity.AlertMetadata;
import com.supervision.supDispatch.Repository.AlertMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AlertService {

    private static final Logger logger = LoggerFactory.getLogger(AlertService.class);

    private final AlertMetadataRepository alertMetadataRepository;

    @Autowired
    public AlertService(AlertMetadataRepository alertMetadataRepository) {
        this.alertMetadataRepository = alertMetadataRepository;
    }

    public Flux<AlertResponse> getAlertData(AlertRequest request) {
        return alertMetadataRepository.findAll()
                .filter(metadata -> (request.getPlatform() == null || metadata.getPlatform().equals(request.getPlatform())) &&
                        (request.getFlow() == null || metadata.getFlow().equals(request.getFlow())) &&
                        (request.getDate() == null || metadata.getDate().equals(request.getDate())))
                .map(this::convertToResponse)
                .doOnNext(response -> logger.debug("Converted to response: " + response))
                .doOnError(e -> logger.error("Error in getAlertData", e));
    }

    private AlertResponse convertToResponse(AlertMetadata alertMetadata) {
        AlertResponse response = new AlertResponse();
        response.setDate(alertMetadata.getDate());
        response.setPlatform(alertMetadata.getPlatform());
        response.setFlow(alertMetadata.getFlow());
        response.setAlerts(alertMetadata.getAlerts());
        return response;
    }

    public Mono<Void> saveOrUpdateAlertData(AlertMetadata data) {
        logger.info("Saving alert data: {}", data);
        return alertMetadataRepository.save(data)
                .doOnSuccess(savedData -> logger.info("Data saved: {}", savedData))
                .doOnError(e -> logger.error("Error saving data", e))
                .then();
    }
}
