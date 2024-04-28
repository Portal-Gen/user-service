package portalgen.userservice.mapper;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import portalgen.userservice.entity.UserProfileEntity;
import portalgen.userservice.model.request.UpdateUserProfileRequest;
import portalgen.userservice.model.request.UserProfileRequest;
import portalgen.userservice.model.response.UserProfileResponse;

@Component
@RequiredArgsConstructor
public class UserProfileMapper {
    public UserProfileEntity toEntity(UserProfileRequest request) {
        UserProfileEntity entity = new UserProfileEntity();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setProfilePicture(request.getProfilePicture());
        entity.setBio(request.getBio());
        entity.setLocation(request.getLocation());
        return entity;
    }
    public UserProfileEntity toEntity(UpdateUserProfileRequest request) {
        UserProfileEntity entity = new UserProfileEntity();
        entity.setId(request.getId());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setProfilePicture(request.getProfilePicture());
        entity.setBio(request.getBio());
        entity.setLocation(request.getLocation());
        return entity;
    }

    public UserProfileResponse toResponse(UserProfileEntity entity) {
        UserProfileResponse response = new UserProfileResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setProfilePicture(entity.getProfilePicture());
        response.setBio(entity.getBio());
        response.setLocation(entity.getLocation());
        return response;
    }

}
