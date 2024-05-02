package portalgen.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portalgen.userservice.entity.PlacePreferenceEntity;
import portalgen.userservice.entity.UserProfileEntity;
import portalgen.userservice.exception.BadRequestError;
import portalgen.userservice.exception.ResponseException;
import portalgen.userservice.repository.PlacePreferenceRepository;
import portalgen.userservice.service.PlacePreferenceRepoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacePreferenceRepoServiceImpl implements PlacePreferenceRepoService {
    private final PlacePreferenceRepository placePreferenceRepository;
    @Override
    public List<PlacePreferenceEntity> saveAll(List<PlacePreferenceEntity> placePreferences) {
        placePreferenceRepository.saveAll(placePreferences);

        return placePreferences;
    }

    @Override
    public List<PlacePreferenceEntity> findPlacePreferenceListByUserEntity(UserProfileEntity userProfileEntity) {
        List <PlacePreferenceEntity> placePreferenceEntities = placePreferenceRepository.findByUserProfileEntity(userProfileEntity);

        if (placePreferenceEntities.isEmpty()) {
            throw new ResponseException(BadRequestError.USER_PROFILE_PLACE_PREFERENCE_NOT_FOUND);
        }

        return placePreferenceEntities;
    }

    @Override
    public boolean existsByUserProfile(UserProfileEntity userProfileEntity) {
        return placePreferenceRepository.existsByUserProfileEntity(userProfileEntity);
    }
}
