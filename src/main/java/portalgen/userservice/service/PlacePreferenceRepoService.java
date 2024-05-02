package portalgen.userservice.service;

import org.springframework.stereotype.Service;
import portalgen.userservice.entity.PlacePreferenceEntity;
import portalgen.userservice.entity.UserProfileEntity;

import java.util.List;

@Service
public interface PlacePreferenceRepoService {
    List<PlacePreferenceEntity> saveAll(List<PlacePreferenceEntity> placePreferences);

    List<PlacePreferenceEntity> findPlacePreferenceListByUserEntity(UserProfileEntity userProfileEntity);

    boolean existsByUserProfile(UserProfileEntity userProfileEntity);


}
