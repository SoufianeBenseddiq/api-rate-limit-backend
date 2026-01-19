package ma.ensa.backend.ws.facade;

import ma.ensa.backend.bean.ApiConsumer;
import ma.ensa.backend.service.facade.ApiConsumerService;
import ma.ensa.backend.ws.converter.ApiConsumerConverter;
import ma.ensa.backend.ws.dto.ApiConsumerDTO;
import ma.ensa.backend.ws.dto.CreateConsumerRequestDTO;
import ma.ensa.backend.ws.dto.UpdateConsumerRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumers")
public class ApiConsumerFacade {

    private final ApiConsumerService consumerService;
    private final ApiConsumerConverter consumerConverter;

    public ApiConsumerFacade(ApiConsumerService consumerService,
                             ApiConsumerConverter consumerConverter) {
        this.consumerService = consumerService;
        this.consumerConverter = consumerConverter;
    }

    /**
     * Créer un nouveau consommateur
     * POST /api/consumers
     */
    @PostMapping
    public ResponseEntity<ApiConsumerDTO> createConsumer(@RequestBody CreateConsumerRequestDTO request) {
        // 1. Appeler le service (travaille avec Entity)
        ApiConsumer entity = consumerService.createApiConsumer(
                request.getName(),
                request.getLimitPerMinute()
        );

        // 2. Convertir Entity → DTO
        ApiConsumerDTO dto = consumerConverter.toDTO(entity);

        // 3. Retourner avec status 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     * Obtenir tous les consommateurs
     * GET /api/consumers
     */
    @GetMapping
    public ResponseEntity<List<ApiConsumerDTO>> getAllConsumers() {
        // 1. Appeler le service
        List<ApiConsumer> entities = consumerService.getAllConsumers();

        // 2. Convertir liste d'Entities → liste de DTOs
        List<ApiConsumerDTO> dtos = consumerConverter.toDTOList(entities);

        // 3. Retourner avec status 200 OK
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtenir un consommateur par ID
     * GET /api/consumers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiConsumerDTO> getConsumerById(@PathVariable Long id) {
        ApiConsumer entity = consumerService.getConsumerById(id);
        ApiConsumerDTO dto = consumerConverter.toDTO(entity);
        return ResponseEntity.ok(dto);
    }

    /**
     * Obtenir un consommateur par API Key
     * GET /api/consumers/by-key/{apiKey}
     */
    @GetMapping("/by-key/{apiKey}")
    public ResponseEntity<ApiConsumerDTO> getConsumerByApiKey(@PathVariable String apiKey) {
        ApiConsumer entity = consumerService.getConsumerByApiKey(apiKey);
        ApiConsumerDTO dto = consumerConverter.toDTO(entity);
        return ResponseEntity.ok(dto);
    }

    /**
     * Mettre à jour un consommateur
     * PUT /api/consumers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiConsumerDTO> updateConsumer(
            @PathVariable Long id,
            @RequestBody UpdateConsumerRequestDTO request) {

        ApiConsumer entity = consumerService.updateConsumer(
                id,
                request.getName(),
                request.getLimitPerMinute()
        );

        ApiConsumerDTO dto = consumerConverter.toDTO(entity);
        return ResponseEntity.ok(dto);
    }

    /**
     * Suspendre un consommateur
     * PATCH /api/consumers/{id}/suspend
     */
    @PatchMapping("/{id}/suspend")
    public ResponseEntity<Void> suspendConsumer(@PathVariable Long id) {
        consumerService.suspendConsumer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Activer un consommateur
     * PATCH /api/consumers/{id}/activate
     */
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateConsumer(@PathVariable Long id) {
        consumerService.activateConsumer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Supprimer un consommateur
     * DELETE /api/consumers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumer(@PathVariable Long id) {
        consumerService.deleteConsumerById(id);
        return ResponseEntity.noContent().build();
    }
}