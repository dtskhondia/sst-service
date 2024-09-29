package ge.bog.sst_service.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Terminal {

    private Long id;

    private Address address;

    private boolean active;

    private LocalDateTime lastAccessTime;

    private List<Provider> availableProviders;
}

