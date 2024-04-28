package portalgen.userservice.service;

import portalgen.userservice.model.enums.PlaceType;
import portalgen.userservice.model.request.PlacePreferenceRequest;
import portalgen.userservice.model.response.PlacePreferenceResponse;

import java.util.Map;

public interface PlacePreferenceService {
    PlacePreferenceResponse setDefaultPlacePreference(Long userProfileId);
    PlacePreferenceResponse getPlacePreference(Long userProfileId);
    PlacePreferenceResponse updatePlacePreference(PlacePreferenceRequest request);
    Map<PlaceType, Float> calculateUserPlacePreferenceScores(Long userProfileId);

}
