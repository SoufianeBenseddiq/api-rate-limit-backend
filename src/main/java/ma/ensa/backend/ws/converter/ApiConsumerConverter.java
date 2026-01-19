package ma.ensa.backend.ws.converter;

import ma.ensa.backend.bean.ApiConsumer;
import ma.ensa.backend.ws.dto.ApiConsumerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiConsumerConverter {

    /**
     * Convertit une Entity ApiConsumer en DTO
     *
     * @param entity L'entity à convertir
     * @return Le DTO correspondant, ou null si entity est null
     */
    public ApiConsumerDTO toDTO(ApiConsumer entity) {
        if (entity == null) {
            return null;
        }

        ApiConsumerDTO dto = new ApiConsumerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setApiKey(entity.getApiKey());
        dto.setLimitPerMinute(entity.getLimitPerMinute());
        dto.setStatus(entity.getStatus().name()); // ACTIVE → "ACTIVE"

        return dto;
    }

    /**
     * Convertit une liste d'entities en liste de DTOs
     *
     * @param entities La liste d'entities à convertir
     * @return La liste de DTOs correspondante
     */
    public List<ApiConsumerDTO> toDTOList(List<ApiConsumer> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
