package com.supervision.supDispatch.Repository;

import com.supervision.supDispatch.Entity.FileMetadata;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FileMetadataRepository extends ReactiveMongoRepository<FileMetadata, String> {
}
