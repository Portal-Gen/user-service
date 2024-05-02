package portalgen.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portalgen.userservice.entity.PlacePreferenceEntity;
import portalgen.userservice.service.PlacePreferenceRepoService;
import portalgen.userservice.service.UserProfileRepoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacePreferenceRepoServiceImpl implements PlacePreferenceRepoService {
    private final UserProfileRepoService userProfileRepoService;
    @Override
    public List<PlacePreferenceEntity> saveAll(List<PlacePreferenceEntity> placePreferences) {
        return null;
    }

}
