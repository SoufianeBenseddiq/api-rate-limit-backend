package ma.ensa.backend.ws.dto;

public class RateLimitCheckResponseDTO {
    private Boolean allowed;
    private Integer currentUsage;

    // Constructeur vide
    public RateLimitCheckResponseDTO() {
    }

    // Constructeur avec paramètres
    public RateLimitCheckResponseDTO(Boolean allowed, Integer currentUsage) {
        this.allowed = allowed;
        this.currentUsage = currentUsage;
    }

    // Getters et Setters
    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public Integer getCurrentUsage() {
        return currentUsage;
    }

    public void setCurrentUsage(Integer currentUsage) {
        this.currentUsage = currentUsage;
    }
}
