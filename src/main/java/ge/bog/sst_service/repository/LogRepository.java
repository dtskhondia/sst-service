package ge.bog.sst_service.repository;

import ge.bog.sst_service.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
}
