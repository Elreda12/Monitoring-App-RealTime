package com.supervision.supDispatch.Repository;

import com.supervision.supDispatch.Entity.AlertMetadata;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AlertMetadataRepository extends ReactiveMongoRepository<AlertMetadata, String> {
}
