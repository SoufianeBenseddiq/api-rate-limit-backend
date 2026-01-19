package ma.ensa.backend.ws.dto;

public class UsageResponseDTO {
    private String apiKey;
    private String windowType;
    private Integer currentUsage;

    // Constructeur vide
    public UsageResponseDTO() {
    }

    // Constructeur avec paramètres
    public UsageResponseDTO(String apiKey, String windowType, Integer currentUsage) {
        this.apiKey = apiKey;
        this.windowType = windowType;
        this.currentUsage = currentUsage;
    }

    // Getters et Setters
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
    }

    public Integer getCurrentUsage() {
        return currentUsage;
    }

    public void setCurrentUsage(Integer currentUsage) {
        this.currentUsage = currentUsage;
    }
}
