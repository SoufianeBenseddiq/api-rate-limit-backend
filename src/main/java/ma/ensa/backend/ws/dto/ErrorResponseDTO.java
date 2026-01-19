package ma.ensa.backend.ws.dto;

public class ErrorResponseDTO {
    private String error;
    private String message;
    private Long timestamp;

    // Constructeur vide
    public ErrorResponseDTO() {
    }

    // Constructeur avec paramètres
    public ErrorResponseDTO(String error, String message, Long timestamp) {
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Constructeur simplifié
    public ErrorResponseDTO(String error) {
        this.error = error;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters et Setters
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
