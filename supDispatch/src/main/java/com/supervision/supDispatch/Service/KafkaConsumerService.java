package com.supervision.supDispatch.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.supervision.supDispatch.Entity.FileMetadata;
import com.supervision.supDispatch.Entity.AlertMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private AlertService alertService;

    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "cdr_topic", groupId = "mygroup")
    public void listenCdrTopic(String message) {
        try {
            FileMetadata fileMetadata = deserializeFileMetadata(message);
            if (fileMetadata.getId() == null) {
                fileMetadata.setId(UUID.randomUUID().toString());
            }
            monitoringService.saveOrUpdateMonitoringData(fileMetadata).subscribe(
                    null,
                    throwable -> System.err.println("Failed to save data: " + throwable.getMessage())
            );
        } catch (Exception e) {
            logger.error("Error while processing message: {}", message, e);
        }
    }

    @KafkaListener(topics = "alert_topic", groupId = "mygroup")
    public void listenAlertsTopic(String message) {
        logger.info("Received message from alerts_topic: {}", message);
        try {
            AlertMetadata alertMetadata = deserializeAlertMetadata(message);
            logger.info("Deserialized alertMetadata: {}", alertMetadata);
            if (alertMetadata.getId() == null) {
                alertMetadata.setId(UUID.randomUUID().toString());
            }
            alertService.saveOrUpdateAlertData(alertMetadata).subscribe(
                    success -> logger.info("Successfully saved alert metadata: {}", alertMetadata),
                    throwable -> logger.error("Failed to save alert metadata: {}", throwable.getMessage())
            );
        } catch (Exception e) {
            logger.error("Error while processing message from alerts_topic: {}", message, e);
        }
    }

    private FileMetadata deserializeFileMetadata(String message) throws Exception {
        return objectMapper.readValue(message, FileMetadata.class);
    }

    private AlertMetadata deserializeAlertMetadata(String message) throws Exception {
        return objectMapper.readValue(message, AlertMetadata.class);
    }
}
