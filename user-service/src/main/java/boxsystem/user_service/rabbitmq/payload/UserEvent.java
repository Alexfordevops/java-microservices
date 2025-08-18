package boxsystem.user_service.rabbitmq.payload;

import java.time.Instant;

public class UserEvent {
    private String eventId;
    private String type;
    private String version;
    private Instant createdAt;
    private UserPayload data;

    public UserEvent() {}

    // getters e setters
    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public UserPayload getData() {
        return data;
    }
    public void setData(UserPayload data) {
        this.data = data;
    }
}
