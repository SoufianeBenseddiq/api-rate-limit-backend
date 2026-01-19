package ma.ensa.backend.service.impl;

import ma.ensa.backend.bean.ApiConsumer;
import ma.ensa.backend.bean.UsageRecord;
import ma.ensa.backend.dao.ApiConsumerRepository;
import ma.ensa.backend.dao.UsageRecordRepository;
import ma.ensa.backend.service.facade.RateLimitService;
import ma.ensa.backend.utils.enums.ConsumerStatus;
import ma.ensa.backend.utils.enums.WindowType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class RateLimitServiceImpl implements RateLimitService {

}
