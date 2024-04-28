package portalgen.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import portalgen.userservice.model.request.UpdateUserProfileRequest;
import portalgen.userservice.model.request.UserProfileRequest;
import portalgen.userservice.model.response.Response;
import portalgen.userservice.model.response.UserProfileResponse;
import portalgen.userservice.service.UserProfileService;

@RestController
@RequestMapping(path = "/api/v1/user-profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping
    public Response<UserProfileResponse> createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        return new Response<>(userProfileService.createUserProfile(userProfileRequest));
    }

    @GetMapping("/{userProfileId}")
    public Response<UserProfileResponse> getUserProfile(@PathVariable Long userProfileId) {
        return new Response<>(userProfileService.getUserProfile(userProfileId));
    }

    @PutMapping
    public Response<UserProfileResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest userProfileRequest) {
        return new Response<>(userProfileService.updateUserProfile(userProfileRequest));
    }

    @DeleteMapping("/{userProfileId}")
    public Response<UserProfileResponse> deleteUserProfile(@PathVariable Long userProfileId) {
        return new Response<>(userProfileService.deleteUserProfile(userProfileId));
    }
}
