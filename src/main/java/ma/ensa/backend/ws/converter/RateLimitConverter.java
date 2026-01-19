package ma.ensa.backend.ws.converter;

import ma.ensa.backend.utils.enums.WindowType;
import ma.ensa.backend.ws.dto.RateLimitCheckResponseDTO;
import ma.ensa.backend.ws.dto.RateLimitRecordResponseDTO;
import ma.ensa.backend.ws.dto.UsageResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class RateLimitConverter {

    /**
     * Crée un DTO de vérification de limite
     *
     * @param allowed Si la requête est autorisée
     * @param currentUsage Nombre de requêtes déjà faites dans la fenêtre actuelle
     * @return Le DTO de réponse
     */
    public RateLimitCheckResponseDTO toCheckResponseDTO(boolean allowed, int currentUsage) {
        RateLimitCheckResponseDTO dto = new RateLimitCheckResponseDTO();
        dto.setAllowed(allowed);
        dto.setCurrentUsage(currentUsage);
        return dto;
    }

    /**
     * Crée un DTO d'enregistrement de requête
     *
     * @param success Si l'enregistrement a réussi
     * @param currentUsage Nombre de requêtes après enregistrement
     * @return Le DTO de réponse
     */
    public RateLimitRecordResponseDTO toRecordResponseDTO(boolean success, int currentUsage) {
        RateLimitRecordResponseDTO dto = new RateLimitRecordResponseDTO();
        dto.setSuccess(success);
        dto.setCurrentUsage(currentUsage);
        return dto;
    }

    /**
     * Crée un DTO d'utilisation
     *
     * @param apiKey La clé API
     * @param windowType Le type de fenêtre
     * @param currentUsage L'utilisation actuelle
     * @return Le DTO de réponse
     */
    public UsageResponseDTO toUsageResponseDTO(String apiKey, WindowType windowType, int currentUsage) {
        UsageResponseDTO dto = new UsageResponseDTO();
        dto.setApiKey(apiKey);
        dto.setWindowType(windowType.name()); // MINUTE → "MINUTE"
        dto.setCurrentUsage(currentUsage);
        return dto;
    }
}
