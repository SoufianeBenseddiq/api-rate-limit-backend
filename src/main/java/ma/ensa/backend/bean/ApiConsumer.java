package ma.ensa.backend.bean;

import jakarta.persistence.*;
import ma.ensa.backend.utils.enums.ConsumerStatus;

@Table(name = "api_consumers")
@Entity
public class ApiConsumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "api_key", unique = true)
    private String apiKey;

    @Column(nullable = false)
    private Integer limitPerMinute;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsumerStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getLimitPerMinute() {
        return limitPerMinute;
    }

    public void setLimitPerMinute(Integer limitPerMinute) {
        this.limitPerMinute = limitPerMinute;
    }

    public ConsumerStatus getStatus() {
        return status;
    }

    public void setStatus(ConsumerStatus status) {
        this.status = status;
    }
}
