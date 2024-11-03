package ge.bog.sst_service.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "logs")
@Data
@Builder
public class LogEntity {
    @Id
    private String id;
    private String method;
    private String uri;
    private String logLevel;
    private Object requestBody;
    private Date requestTime;
    private Object responseBody;
    private Date responseTime;
}
