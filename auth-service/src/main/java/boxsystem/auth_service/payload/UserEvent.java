package boxsystem.auth_service.payload;

import java.time.Instant;

public class UserEvent {
    private String eventId;      // UUID
    private String type;         // user.created|user.updated|user.deleted
    private String version = "1";
    private Instant occurredAt;
    private UserPayload data;

    public UserEvent(String eventId, String type, String version, Instant occurredAt, UserPayload data) {
        this.eventId = eventId;
        this.type = type;
        this.version = version;
        this.occurredAt = occurredAt;
        this.data = data;
    }

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
    public Instant getOccurredAt() {
        return occurredAt;
    }
    public void setOccurredAt(Instant occurredAt) {
        this.occurredAt = occurredAt;
    }
    public UserPayload getData() {
        return data;
    }
    public void setData(UserPayload data) {
        this.data = data;
    }
}
