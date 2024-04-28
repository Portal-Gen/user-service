package portalgen.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import portalgen.userservice.model.enums.SocialMedialPlatform;

@Entity
@Table(name = "user_social_profile")
@Data
public class SocialProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfileEntity userProfileEntity;
    @Column(name = "platform")
    @Enumerated(EnumType.STRING)
    private SocialMedialPlatform platform;
    @Column(name = "social_profile_url")
    private String socialProfileUrl;
}
