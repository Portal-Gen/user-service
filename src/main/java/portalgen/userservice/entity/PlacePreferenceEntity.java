package portalgen.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import portalgen.userservice.model.enums.PlaceType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_place_preference")
@Data
public class PlacePreferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfileEntity userProfileEntity;
    @Column(name = "preference_key")
    private PlaceType preferenceKey;
    @Column(name = "preference_value")
    private Float preferenceValue;
}

