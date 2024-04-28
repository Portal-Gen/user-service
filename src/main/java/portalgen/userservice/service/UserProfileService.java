package portalgen.userservice.service;

import portalgen.userservice.model.request.UpdateUserProfileRequest;
import portalgen.userservice.model.request.UserProfileRequest;
import portalgen.userservice.model.response.UserProfileResponse;

public interface UserProfileService {
    UserProfileResponse createUserProfile(UserProfileRequest userProfileRequest);
    UserProfileResponse getUserProfile(Long userProfileId);
    UserProfileResponse updateUserProfile(UpdateUserProfileRequest userProfileRequest);
    UserProfileResponse deleteUserProfile(Long userProfileId);
}
