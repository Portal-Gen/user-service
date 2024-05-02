package portalgen.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portalgen.userservice.entity.PlacePreferenceEntity;
import portalgen.userservice.entity.UserProfileEntity;
import portalgen.userservice.exception.BadRequestError;
import portalgen.userservice.exception.ResponseException;
import portalgen.userservice.mapper.PlacePreferenceMapper;
import portalgen.userservice.model.enums.PlaceType;
import portalgen.userservice.model.request.PlacePreferenceRequest;
import portalgen.userservice.model.response.PlacePreferenceResponse;
import portalgen.userservice.repository.PlacePreferenceRepository;
import portalgen.userservice.repository.UserProfileRepository;
import portalgen.userservice.service.PlacePreferenceRepoService;
import portalgen.userservice.service.PlacePreferenceService;
import portalgen.userservice.service.UserProfileRepoService;
import portalgen.userservice.service.UserProfileService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlacePreferenceServiceImpl implements PlacePreferenceService {
    private final PlacePreferenceRepoService placePreferenceRepoService;
    private final UserProfileRepoService userProfileRepoService;

    @Override
    public PlacePreferenceResponse setDefaultPlacePreference(Long userProfileId) {
        UserProfileEntity userProfileEntity = userProfileRepoService.findByUserId(userProfileId);
        List<PlacePreferenceEntity> placePreferences;

        if (placePreferenceRepoService.existsByUserProfile(userProfileEntity)) {
            placePreferences = new ArrayList<>(placePreferenceRepoService.findPlacePreferenceListByUserEntity(userProfileEntity));
            placePreferences.forEach(preference -> preference.setPreferenceValue(0.5f));
        } else {
            Map<PlaceType, Float> defaultPlacePreference = getDefaultPlacePreference();
            PlacePreferenceRequest request = new PlacePreferenceRequest(userProfileId, defaultPlacePreference);
            placePreferences = new ArrayList<>(PlacePreferenceMapper.toEntities(request, userProfileEntity));
        }
        List<PlacePreferenceEntity> updatedPlacePreferences = placePreferenceRepoService.saveAll(placePreferences);

        return PlacePreferenceMapper.toResponse(userProfileId, updatedPlacePreferences);
    }

    @Override
    public PlacePreferenceResponse getPlacePreference(Long userProfileId) {
        UserProfileEntity userProfileEntity = userProfileRepoService.findByUserId(userProfileId);

        List<PlacePreferenceEntity> placePreferences = placePreferenceRepoService.findPlacePreferenceListByUserEntity(userProfileEntity);

        return PlacePreferenceMapper.toResponse(userProfileId, placePreferences);
    }

    @Override
    public PlacePreferenceResponse updatePlacePreference(PlacePreferenceRequest request) {
        UserProfileEntity userProfile = userProfileRepoService.findByUserId(request.getUserProfileId());
        List<PlacePreferenceEntity> placePreferences = placePreferenceRepoService.findPlacePreferenceListByUserEntity(userProfile);

        Map<PlaceType, Float> preferences = request.getPreferences();

        placePreferences.forEach(preference -> {
            if (preferences.containsKey(preference.getPreferenceKey())) {
                preference.setPreferenceValue(preferences.get(preference.getPreferenceKey()));
            }
        });

        List<PlacePreferenceEntity> savedPlacePreferences = placePreferenceRepoService.saveAll(placePreferences);

        return PlacePreferenceMapper.toResponse(request.getUserProfileId(), savedPlacePreferences);
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
