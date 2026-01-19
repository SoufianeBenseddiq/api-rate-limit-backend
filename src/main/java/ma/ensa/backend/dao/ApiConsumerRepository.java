package ma.ensa.backend.dao;


import ma.ensa.backend.bean.ApiConsumer;
import ma.ensa.backend.utils.enums.ConsumerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiConsumerRepository extends JpaRepository<ApiConsumer, Long> {
    Optional<ApiConsumer> findByApiKey(String apiKey);
    boolean existsByApiKey(String apiKey);
    Optional<ApiConsumer> findByApiKeyAndStatus(String apiKey, ConsumerStatus status);
}
