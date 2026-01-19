package ma.ensa.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ma.ensa.backend.bean.ApiConsumer;
import ma.ensa.backend.dao.ApiConsumerRepository;
import ma.ensa.backend.service.facade.ApiConsumerService;
import ma.ensa.backend.utils.enums.ConsumerStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class ApiConsumerServiceImpl implements ApiConsumerService {

    ApiConsumerRepository consumerRepository;
    public ApiConsumerServiceImpl(ApiConsumerRepository apiConsumerRepository) {
        this.consumerRepository = apiConsumerRepository;
    }

    @Override
    public ApiConsumer createApiConsumer(String name, Integer limitPerMinute) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(limitPerMinute == null || limitPerMinute <= 0){
            throw new IllegalArgumentException("Limit per minute cannot be null or negative");
        }
        ApiConsumer apiConsumer = new ApiConsumer();
        apiConsumer.setName(name);
        apiConsumer.setLimitPerMinute(limitPerMinute);
        apiConsumer.setApiKey(generateConsumerApiKey());
        apiConsumer.setStatus(ConsumerStatus.ACTIVE);
        return consumerRepository.save(apiConsumer);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiConsumer getConsumerByApiKey(String apiKey) {
        if(apiKey == null || apiKey.trim().isEmpty()){
            throw new IllegalArgumentException("Api key cannot be null or empty");
        }
        return consumerRepository.findByApiKey(apiKey).orElseThrow(() -> new IllegalArgumentException("Api key not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public ApiConsumer getConsumerById(Long id){
        if(id == null || id.intValue() <= 0){
            throw new IllegalArgumentException("Id cannot be null or negative");
        }
        return consumerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Consumer with id not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApiConsumer> getAllConsumers() {
        return consumerRepository.findAll();
    }

    @Override
    public ApiConsumer updateConsumer(Long id, String name, Integer limitPerMinute) {
        ApiConsumer consumer = getConsumerById(id);

        if (name != null && !name.trim().isEmpty()) {
            consumer.setName(name);
        }
        if (limitPerMinute != null && limitPerMinute > 0) {
            consumer.setLimitPerMinute(limitPerMinute);
        }

        return consumerRepository.save(consumer);
    }

    @Override
    public void activateConsumer(Long id) {
        ApiConsumer consumer = getConsumerById(id);
        consumer.setStatus(ConsumerStatus.ACTIVE);
        consumerRepository.save(consumer);
    }

    @Override
    public void suspendConsumer(Long id){
        ApiConsumer consumer = getConsumerById(id);
        consumer.setStatus(ConsumerStatus.SUSPENDED);
        consumerRepository.save(consumer);
    }

    @Override
    public void deleteConsumerById(Long id){
        ApiConsumer consumer = getConsumerById(id);
        consumerRepository.delete(consumer);
    }

    private String generateConsumerApiKey() {
        String apiKey ;
        do{
            apiKey = UUID.randomUUID().toString().replace("-", "");
        } while( consumerRepository.existsByApiKey(apiKey) );
        return apiKey;
    }
}