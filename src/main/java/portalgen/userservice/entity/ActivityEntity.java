package portalgen.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import portalgen.userservice.model.enums.ActivityType;

import java.security.Timestamp;

@Entity
@Table(name = "user_activity")
@Data
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfileEntity userProfileEntity;
    private ActivityType activityType;
    private String activityData;
    private Timestamp activityTimestamp;

    public void setSearchDescription(String destination, String searchQuery) {
        this.activityType= ActivityType.SEARCH_DESTINATION;
        this.activityData = String.format("{\"destination\": \"%s\", \"searchQuery\": \"%s\"}", destination, searchQuery);
    }
    //TODO: Add more activity type setters
}
