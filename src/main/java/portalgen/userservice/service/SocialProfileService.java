package portalgen.userservice.service;

import portalgen.userservice.model.request.SocialProfileRequest;
import portalgen.userservice.model.response.SocialProfileResponse;

public interface SocialProfileService {
    SocialProfileResponse addSocialProfile(SocialProfileRequest request);
    SocialProfileResponse getSocialProfile(Long userProfileId);
    SocialProfileResponse updateSocialProfile(SocialProfileRequest request);
    SocialProfileResponse deleteSocialProfile(Long userProfileId);
}
