package ge.bog.sst_service.repository;

import ge.bog.sst_service.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogEntity, String> {
}
