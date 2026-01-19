package ma.ensa.backend.service.facade;

import ma.ensa.backend.utils.enums.WindowType;

public interface RateLimitService {
    boolean checkRateLimit(String apiKey);
    void recordRequest(String apiKey);
    int getCurrentUsage(String apiKey, WindowType windowType);

}
