package ma.ensa.backend.ws.dto;

public class CreateConsumerRequestDTO {
    private String name;
    private Integer limitPerMinute;

    // Constructeur vide
    public CreateConsumerRequestDTO() {
    }

    // Constructeur avec paramètres
    public CreateConsumerRequestDTO(String name, Integer limitPerMinute) {
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
