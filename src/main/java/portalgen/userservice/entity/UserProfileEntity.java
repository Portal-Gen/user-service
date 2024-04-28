package portalgen.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "user_profile")
@Data
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private String email;
    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;
    private String bio;
    private String location;

    @OneToMany(mappedBy = "userProfileEntity", cascade = CascadeType.ALL)
    private List<PlacePreferenceEntity> placePreferences;

    @OneToMany(mappedBy = "userProfileEntity", cascade = CascadeType.ALL)
    private List<ActivityEntity> activities;

    @OneToMany(mappedBy = "userProfileEntity", cascade = CascadeType.ALL)
    private List<SocialProfileEntity> socialProfiles;

    @OneToMany(mappedBy = "userProfileEntity", cascade = CascadeType.ALL)
    private List<RatingEntity> ratings;

    @OneToMany(mappedBy = "userProfileEntity", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews;
}
