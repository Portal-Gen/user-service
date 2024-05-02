package portalgen.userservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import portalgen.userservice.entity.PlacePreferenceEntity;
import portalgen.userservice.entity.UserProfileEntity;
import portalgen.userservice.model.enums.PlaceType;
import portalgen.userservice.model.request.PlacePreferenceRequest;
import portalgen.userservice.model.response.PlacePreferenceResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlacePreferenceMapper {

    public static List<PlacePreferenceEntity> toEntities(PlacePreferenceRequest request, UserProfileEntity userProfileEntity) {
        return request.getPreferences().entrySet().stream()
                .map(entry -> {
                    PlacePreferenceEntity preference = new PlacePreferenceEntity();
                    preference.setUserProfileEntity(userProfileEntity);
                    preference.setPreferenceKey(entry.getKey());
                    preference.setPreferenceValue(entry.getValue());
                    return preference;
                })
                .collect(Collectors.toList());
    }

    public static PlacePreferenceResponse toResponse(Long userId, List<PlacePreferenceEntity> preferences) {
        PlacePreferenceResponse response = new PlacePreferenceResponse();
        response.setUserProfileId(userId);

        Map<PlaceType, Float> preferenceMap = preferences.stream()
                .collect(Collectors.toMap(PlacePreferenceEntity::getPreferenceKey, PlacePreferenceEntity::getPreferenceValue));

        response.setPreferences(preferenceMap);

        return response;
    }
}
