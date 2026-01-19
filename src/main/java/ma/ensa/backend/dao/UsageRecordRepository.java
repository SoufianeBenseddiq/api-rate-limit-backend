package ma.ensa.backend.dao;

import ma.ensa.backend.bean.ApiConsumer;
import ma.ensa.backend.bean.UsageRecord;
import ma.ensa.backend.utils.enums.WindowType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsageRecordRepository extends JpaRepository<UsageRecord, Long> {

}