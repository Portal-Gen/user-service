package portalgen.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portalgen.userservice.entity.PlacePreferenceEntity;
import portalgen.userservice.entity.UserProfileEntity;
import portalgen.userservice.exception.BadRequestError;
import portalgen.userservice.exception.ResponseException;
import portalgen.userservice.model.enums.PlaceType;
import portalgen.userservice.model.request.PlacePreferenceRequest;
import portalgen.userservice.model.response.PlacePreferenceResponse;
import portalgen.userservice.repository.PlacePreferenceRepository;
import portalgen.userservice.repository.UserProfileRepository;
import portalgen.userservice.service.PlacePreferenceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlacePreferenceServiceImpl implements PlacePreferenceService {
    private final UserProfileRepository userProfileRepository;
    private final PlacePreferenceRepository placePreferenceRepository;

    @Autowired
    public PlacePreferenceServiceImpl(UserProfileRepository userProfileRepository,
                                      PlacePreferenceRepository placePreferenceRepository) {
        this.userProfileRepository = userProfileRepository;
        this.placePreferenceRepository = placePreferenceRepository;
    }

    @Override
    public PlacePreferenceResponse setDefaultPlacePreference(Long userProfileId) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(userProfileId).orElseThrow(() -> new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND));

        List<PlacePreferenceEntity> existingPlacePreferences = placePreferenceRepository.findByUserProfileEntity(userProfileEntity);

        if (!existingPlacePreferences.isEmpty()) {
            placePreferenceRepository.deleteAll(existingPlacePreferences);
        }

        Map<PlaceType, Float> defaultPlacePreference = getDefaultPlacePreference();

        List<PlacePreferenceEntity> placePreferences = defaultPlacePreference.entrySet().stream()
                .map(entry -> {
                    PlacePreferenceEntity preference = new PlacePreferenceEntity();
                    preference.setUserProfileEntity(userProfileEntity);
                    preference.setPreferenceKey(entry.getKey());
                    preference.setPreferenceValue(entry.getValue());
                    return preference;
                })
                .collect(Collectors.toList());

        placePreferenceRepository.saveAll(placePreferences);

        PlacePreferenceResponse response = new PlacePreferenceResponse();
        response.setUserProfileId(userProfileEntity.getId());
        response.setPreferences(defaultPlacePreference);

        return response;
    }

    @Override
    public PlacePreferenceResponse getPlacePreference(Long userProfileId) {
        UserProfileEntity userProfile = userProfileRepository.findById(userProfileId).orElseThrow(() -> new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND));

        List<PlacePreferenceEntity> placePreferences = placePreferenceRepository.findByUserProfileEntity(userProfile);

        Map<PlaceType, Float> preferences = placePreferences.stream()
                .collect(Collectors.toMap(PlacePreferenceEntity::getPreferenceKey, PlacePreferenceEntity::getPreferenceValue));

        PlacePreferenceResponse response = new PlacePreferenceResponse();
        response.setUserProfileId(userProfileId);
        response.setPreferences(preferences);

        return response;
    }

    @Override
    public PlacePreferenceResponse updatePlacePreference(PlacePreferenceRequest request) {
        UserProfileEntity userProfile = userProfileRepository.findById(request.getUserProfileId()).orElseThrow(() -> new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND));

        List<PlacePreferenceEntity> placePreferences = placePreferenceRepository.findByUserProfileEntity(userProfile);

        Map<PlaceType, Float> preferences = request.getPreferences();

        placePreferences.forEach(preference -> {
            if (preferences.containsKey(preference.getPreferenceKey())) {
                preference.setPreferenceValue(preferences.get(preference.getPreferenceKey()));
            }
        });

        placePreferenceRepository.saveAll(placePreferences);

        PlacePreferenceResponse response = new PlacePreferenceResponse();
        response.setUserProfileId(userProfile.getId());
        response.setPreferences(preferences);

        return response;
    }


    private Map<PlaceType, Float> getDefaultPlacePreference() {
        Map<PlaceType, Float> defaultPlacePreference = new HashMap<>();
        for (PlaceType placeType : PlaceType.values()) {
            defaultPlacePreference.put(placeType, 0.5f);
        }
        return defaultPlacePreference;
    }

    @Override
    public Map<PlaceType, Float> calculateUserPlacePreferenceScores(Long userProfileId) {
        return null;
    }
}
