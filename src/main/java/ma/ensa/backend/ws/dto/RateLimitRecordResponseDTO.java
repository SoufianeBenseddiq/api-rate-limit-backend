package ma.ensa.backend.ws.dto;

public class RateLimitRecordResponseDTO {
    private Boolean success;
    private Integer currentUsage;

    // Constructeur vide
    public RateLimitRecordResponseDTO() {
    }

    // Constructeur avec paramètres
    public RateLimitRecordResponseDTO(Boolean success, Integer currentUsage) {
        this.success = success;
        this.currentUsage = currentUsage;
    }

    // Getters et Setters
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCurrentUsage() {
        return currentUsage;
    }

    public void setCurrentUsage(Integer currentUsage) {
        this.currentUsage = currentUsage;
    }
}