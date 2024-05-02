package portalgen.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portalgen.userservice.entity.PlacePreferenceEntity;
import portalgen.userservice.entity.UserProfileEntity;

import java.util.List;

@Repository
public interface PlacePreferenceRepository extends JpaRepository<PlacePreferenceEntity, Long> {
    List<PlacePreferenceEntity> findByUserProfileEntity(UserProfileEntity userProfileEntity);

    boolean existsByUserProfileEntity(UserProfileEntity userProfileEntity);
}
