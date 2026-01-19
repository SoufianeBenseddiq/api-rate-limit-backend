package ma.ensa.backend.ws.converter;


import ma.ensa.backend.ws.dto.ErrorResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ErrorConverter {

    /**
     * Crée un DTO d'erreur simple
     *
     * @param error Le message d'erreur
     * @return Le DTO d'erreur avec timestamp
     */
    public ErrorResponseDTO toErrorDTO(String error) {
        ErrorResponseDTO dto = new ErrorResponseDTO();
        dto.setError(error);
        dto.setTimestamp(System.currentTimeMillis());
        return dto;
    }

    /**
     * Crée un DTO d'erreur avec message détaillé
     *
     * @param error Le type d'erreur (ex: "Bad Request")
     * @param message Le message détaillé
     * @return Le DTO d'erreur complet
     */
    public ErrorResponseDTO toErrorDTO(String error, String message) {
        ErrorResponseDTO dto = new ErrorResponseDTO();
        dto.setError(error);
        dto.setMessage(message);
        dto.setTimestamp(System.currentTimeMillis());
        return dto;
    }
}