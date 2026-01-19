package ma.ensa.backend.bean;

import jakarta.persistence.*;
import ma.ensa.backend.utils.enums.WindowType;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "usage_records",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"consumer_id", "window_type", "window_start"}
                )
        }
)
public class UsageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private ApiConsumer consumer;

    @Enumerated(EnumType.STRING)
    @Column(name = "window_type", nullable = false)
    private WindowType windowType;

    @Column(name = "window_start", nullable = false)
    private LocalDateTime windowStart;

    @Column(nullable = false)
    private Integer requestCount = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(ApiConsumer consumer) {
        this.consumer = consumer;
    }

    public WindowType getWindowType() {
        return windowType;
    }

    public void setWindowType(WindowType windowType) {
        this.windowType = windowType;
    }

    public LocalDateTime getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(LocalDateTime windowStart) {
        this.windowStart = windowStart;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }
}
