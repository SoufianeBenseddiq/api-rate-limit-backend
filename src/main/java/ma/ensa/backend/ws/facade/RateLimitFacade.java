package ma.ensa.backend.ws.facade;

import ma.ensa.backend.service.facade.RateLimitService;
import ma.ensa.backend.utils.enums.WindowType;
import ma.ensa.backend.ws.converter.ErrorConverter;
import ma.ensa.backend.ws.converter.RateLimitConverter;
import ma.ensa.backend.ws.dto.ErrorResponseDTO;
import ma.ensa.backend.ws.dto.RateLimitCheckResponseDTO;
import ma.ensa.backend.ws.dto.RateLimitRecordResponseDTO;
import ma.ensa.backend.ws.dto.UsageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rate-limit")
public class RateLimitFacade {

    private final RateLimitService rateLimitService;
    private final RateLimitConverter rateLimitConverter;
    private final ErrorConverter errorConverter;

    public RateLimitFacade(RateLimitService rateLimitService,
                           RateLimitConverter rateLimitConverter,
                           ErrorConverter errorConverter) {
        this.rateLimitService = rateLimitService;
        this.rateLimitConverter = rateLimitConverter;
        this.errorConverter = errorConverter;
    }

    /**
     * Vérifier si une requête est autorisée (sans l'enregistrer)
     * POST /api/rate-limit/check?apiKey=xxx
     */
    @PostMapping("/check")
    public ResponseEntity<?> checkRateLimit(@RequestParam String apiKey) {
        try {
            // 1. Vérifier la limite
            boolean allowed = rateLimitService.checkRateLimit(apiKey);

            // 2. Obtenir l'utilisation actuelle
            int currentUsage = rateLimitService.getCurrentUsage(apiKey, WindowType.MINUTE);

            // 3. Créer le DTO de réponse
            RateLimitCheckResponseDTO dto = rateLimitConverter.toCheckResponseDTO(allowed, currentUsage);

            // 4. Retourner 200 OK
            return ResponseEntity.ok(dto);

        } catch (IllegalArgumentException e) {
            // API Key invalide → 404 Not Found
            ErrorResponseDTO error = errorConverter.toErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        } catch (IllegalStateException e) {
            // Consommateur suspendu → 403 Forbidden
            ErrorResponseDTO error = errorConverter.toErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        }
    }

    /**
     * Enregistrer une requête (incrémenter le compteur)
     * POST /api/rate-limit/record?apiKey=xxx
     */
    @PostMapping("/record")
    public ResponseEntity<?> recordRequest(@RequestParam String apiKey) {
        try {
            // 1. Enregistrer la requête (peut lancer exception si limite dépassée)
            rateLimitService.recordRequest(apiKey);

            // 2. Obtenir l'utilisation actuelle
            int currentUsage = rateLimitService.getCurrentUsage(apiKey, WindowType.MINUTE);

            // 3. Créer le DTO de réponse
            RateLimitRecordResponseDTO dto = rateLimitConverter.toRecordResponseDTO(true, currentUsage);

            // 4. Retourner 200 OK
            return ResponseEntity.ok(dto);

        } catch (IllegalArgumentException e) {
            // API Key invalide → 404 Not Found
            ErrorResponseDTO error = errorConverter.toErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        } catch (IllegalStateException e) {
            // Limite dépassée ou consommateur suspendu → 429 Too Many Requests
            ErrorResponseDTO error = errorConverter.toErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(error);
        }
    }

    /**
     * Obtenir l'utilisation actuelle
     * GET /api/rate-limit/usage?apiKey=xxx&windowType=MINUTE
     */
    @GetMapping("/usage")
    public ResponseEntity<?> getCurrentUsage(
            @RequestParam String apiKey,
            @RequestParam(defaultValue = "MINUTE") WindowType windowType) {

        try {
            // 1. Obtenir l'utilisation
            int usage = rateLimitService.getCurrentUsage(apiKey, windowType);

            // 2. Créer le DTO de réponse
            UsageResponseDTO dto = rateLimitConverter.toUsageResponseDTO(apiKey, windowType, usage);

            // 3. Retourner 200 OK
            return ResponseEntity.ok(dto);

        } catch (IllegalArgumentException e) {
            // API Key invalide → 404 Not Found
            ErrorResponseDTO error = errorConverter.toErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}