package ma.ensa.backend.ws.dto;

public class ApiConsumerDTO {
    private Long id;
    private String name;
    private String apiKey;
    private Integer limitPerMinute;
    private String status;

    // Constructeur vide (obligatoire pour Jackson)
    public ApiConsumerDTO() {
    }

    // Constructeur avec tous les paramètres
    public ApiConsumerDTO(Long id, String name, String apiKey, Integer limitPerMinute, String status) {
        this.id = id;
        this.name = name;
        this.apiKey = apiKey;
        this.limitPerMinute = limitPerMinute;
        this.status = status;
    }

    // Getters et Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
