package portalgen.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_rating")
@Data
@NoArgsConstructor
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfileEntity userProfileEntity;

    @Column(name = "place_id", nullable = false)
    private String placeId;

    @Column(name = "rating", nullable = false)
    private int rating;
}
