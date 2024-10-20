package ge.bog.sst_service.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "logs")
@Data
public class Log {
    @Id
    private String id;
    private String method;
    private String uri;
    private String requestBody;
    private String responseBody;
    private Date logTime;
}
