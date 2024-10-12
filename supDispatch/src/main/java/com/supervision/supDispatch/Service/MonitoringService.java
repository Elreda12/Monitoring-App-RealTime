package com.supervision.supDispatch.Service;

import com.supervision.supDispatch.Dto.MonitoringRequest;
import com.supervision.supDispatch.Dto.MonitoringResponse;
import com.supervision.supDispatch.Entity.FileMetadata;
import com.supervision.supDispatch.Repository.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    private final FileMetadataRepository fileMetadataRepository;

    @Autowired
    public MonitoringService(FileMetadataRepository fileMetadataRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }

    public Flux<MonitoringResponse> getMonitoringData(MonitoringRequest request) {
        return fileMetadataRepository.findAll()
                .filter(metadata -> (request.getPlatform() == null || metadata.getPlatform().equals(request.getPlatform())) &&
                        (request.getFlow() == null || metadata.getFlow().equals(request.getFlow())) &&
                        (request.getDate() == null || metadata.getDate().equals(request.getDate())))
                .map(this::convertToResponse)
                .doOnNext(response -> logger.debug("Converted to response: " + response))
                .doOnError(e -> logger.error("Error in getMonitoringData", e));
    }

    private MonitoringResponse convertToResponse(FileMetadata fileMetadata) {
        MonitoringResponse response = new MonitoringResponse();
        response.setDate(fileMetadata.getDate());
        response.setPlatform(fileMetadata.getPlatform());
        response.setFlow(fileMetadata.getFlow());
        response.setTicketsDecoded(fileMetadata.getTicketsDecoded());
        response.setTicketsSentM6(fileMetadata.getTicketsSentM6());
        response.setTicketsSentM7(fileMetadata.getTicketsSentM7());
        response.setFileSizeTransferredM6(fileMetadata.getFileSizeTransferredM6());
        response.setFileSizeTransferredM7(fileMetadata.getFileSizeTransferredM7());
        return response;
    }

    public Mono<Void> saveOrUpdateMonitoringData(FileMetadata data) {
        return fileMetadataRepository.save(data).then();
    }
}
