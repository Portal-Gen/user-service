package portalgen.userservice.mapper;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import portalgen.userservice.entity.UserProfileEntity;
import portalgen.userservice.exception.BadRequestError;
import portalgen.userservice.exception.ResponseException;
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

    public UserProfileEntity toUpdatedEntity(UserProfileRequest request, UserProfileEntity oldEntity) {
        UserProfileEntity updatedEntity = new UserProfileEntity();

        if (request.getFirstName() != null) {
            updatedEntity.setFirstName(request.getFirstName());
        } else {
            updatedEntity.setFirstName(oldEntity.getFirstName());
        }

        if (request.getLastName() != null) {
            updatedEntity.setLastName(request.getLastName());
        } else {
            updatedEntity.setLastName(oldEntity.getLastName());
        }

        if (request.getEmail() != null && !request.getEmail().equals(oldEntity.getEmail())) {
            throw new ResponseException(BadRequestError.USER_EMAIL_CANNOT_BE_UPDATED, "User email cannot be updated. Only first name, last name, profile picture, bio, and location can be updated.");
        } else {
            updatedEntity.setEmail(oldEntity.getEmail());
        }

        if (request.getProfilePicture() != null) {
            updatedEntity.setProfilePicture(request.getProfilePicture());
        } else {
            updatedEntity.setProfilePicture(oldEntity.getProfilePicture());
        }

        if (request.getBio() != null) {
            updatedEntity.setBio(request.getBio());
        } else {
            updatedEntity.setBio(oldEntity.getBio());
        }

        if (request.getLocation() != null) {
            updatedEntity.setLocation(request.getLocation());
        } else {
            updatedEntity.setLocation(oldEntity.getLocation());
        }

        return updatedEntity;
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
