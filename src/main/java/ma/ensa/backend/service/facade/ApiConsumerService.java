package ma.ensa.backend.service.facade;

import ma.ensa.backend.bean.ApiConsumer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ApiConsumerService {

    ApiConsumer createApiConsumer(String name, Integer limitPerMinute);

    ApiConsumer getConsumerByApiKey(String apiKey);

    @Transactional(readOnly = true)
    ApiConsumer getConsumerById(Long id);

    @Transactional(readOnly = true)
    List<ApiConsumer> getAllConsumers();

    ApiConsumer updateConsumer(Long id, String name, Integer limitPerMinute);

    void activateConsumer(Long id);

    void suspendConsumer(Long id);

    void deleteConsumerById(Long id);

}
