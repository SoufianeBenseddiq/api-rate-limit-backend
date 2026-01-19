package ma.ensa.backend.ws.dto;

public class UpdateConsumerRequestDTO {
    private String name;
    private Integer limitPerMinute;

    // Constructeur vide
    public UpdateConsumerRequestDTO() {
    }

    // Constructeur avec paramètres
    public UpdateConsumerRequestDTO(String name, Integer limitPerMinute) {
        this.name = name;
        this.limitPerMinute = limitPerMinute;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimitPerMinute() {
        return limitPerMinute;
    }

    public void setLimitPerMinute(Integer limitPerMinute) {
        this.limitPerMinute = limitPerMinute;
    }
}