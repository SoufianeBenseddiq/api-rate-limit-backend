package ma.ensa.backend.service.impl;

import ma.ensa.backend.bean.ApiConsumer;
import ma.ensa.backend.bean.UsageRecord;
import ma.ensa.backend.service.facade.ApiConsumerService;
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

    ApiConsumerService consumerService;
    UsageRecordRepository usageRecordRepository;
    public RateLimitServiceImpl(UsageRecordRepository usageRecordRepository, ApiConsumerService consumerService) {
        this.usageRecordRepository = usageRecordRepository;
        this.consumerService = consumerService;
    }

    @Override
    public boolean checkRateLimit(String apiKey) {
        ApiConsumer consumer = consumerService.getConsumerByApiKey(apiKey);
        if (consumer.getStatus()!= ConsumerStatus.ACTIVE) {
            throw new IllegalStateException("Consumer is not active");
        }
        LocalDateTime windowStart = getWindowStart(WindowType.MINUTE);
        UsageRecord record = findOrCreateUsageRecord(consumer, WindowType.MINUTE, windowStart);
        return consumer.getLimitPerMinute() > record.getRequestCount();
    }

    @Override
    public void recordRequest(String apiKey) {
        ApiConsumer consumer = consumerService.getConsumerByApiKey(apiKey);
        if (consumer.getStatus() == ConsumerStatus.SUSPENDED) {
            throw new IllegalStateException("Consumer is suspended");
        }
        LocalDateTime windowStart = getWindowStart(WindowType.MINUTE);
        UsageRecord record = findOrCreateUsageRecord(consumer, WindowType.MINUTE, windowStart);
        if (record.getRequestCount() >= consumer.getLimitPerMinute()) {
            throw new IllegalStateException("Rate limit exceeded");
        }
        record.setRequestCount(record.getRequestCount() + 1);
        usageRecordRepository.save(record);
    }

    @Override
    public int getCurrentUsage(String apiKey, WindowType windowType) {
        ApiConsumer consumer = consumerService.getConsumerByApiKey(apiKey);
        LocalDateTime windowStart = getWindowStart(windowType);
        return usageRecordRepository
                .findByConsumerAndWindowTypeAndWindowStart(consumer, windowType, windowStart)
                .map(UsageRecord::getRequestCount)
                .orElse(0);
    }

    private UsageRecord findOrCreateUsageRecord(ApiConsumer consumer, WindowType windowType, LocalDateTime windowStart) {
        return usageRecordRepository.findByConsumerAndWindowTypeAndWindowStart(consumer, windowType, windowStart)
                .orElseGet(()->{
                    UsageRecord usageRecord = new UsageRecord();
                    usageRecord.setConsumer(consumer);
                    usageRecord.setWindowStart(windowStart);
                    usageRecord.setWindowType(windowType);
                    return usageRecordRepository.save(usageRecord);
                });
    }

    private LocalDateTime getWindowStart( WindowType windowType) {
        LocalDateTime now = LocalDateTime.now();
        switch (windowType) {
            case MINUTE:
                return now.truncatedTo(ChronoUnit.MINUTES);
            case HOUR:
                return now.truncatedTo(ChronoUnit.HOURS);
            default:
                throw new IllegalArgumentException("Invalid window type");
        }
    }
}
