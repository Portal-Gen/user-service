package portalgen.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portalgen.userservice.entity.UserProfileEntity;
import portalgen.userservice.exception.BadRequestError;
import portalgen.userservice.exception.ResponseException;
import portalgen.userservice.mapper.UserProfileMapper;
import portalgen.userservice.model.request.UpdateUserProfileRequest;
import portalgen.userservice.model.request.UserProfileRequest;
import portalgen.userservice.model.response.UserProfileResponse;
import portalgen.userservice.repository.UserProfileRepository;
import portalgen.userservice.service.UserProfileRepoService;
import portalgen.userservice.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepoService userProfileRepoService;
    private final UserProfileMapper mapper;
    @Autowired
    public UserProfileServiceImpl(UserProfileRepoService userProfileRepoService, UserProfileMapper mapper) {
        this.mapper = mapper;
        this.userProfileRepoService = userProfileRepoService;
    }
    @Override
    public UserProfileResponse createUserProfile(UserProfileRequest userProfileRequest)
    {
        this.handleCreateUserProfileRequest(userProfileRequest);
        
        UserProfileEntity entity = mapper.toEntity(userProfileRequest);
        UserProfileEntity savedEntity = userProfileRepoService.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Override
    public UserProfileResponse getUserProfile(Long userProfileId) {
        UserProfileEntity userProfileEntity = userProfileRepoService.findByUserId(userProfileId);

        return mapper.toResponse(userProfileEntity);
    }

    @Override
    public UserProfileResponse updateUserProfile(UpdateUserProfileRequest userProfileRequest) {
        if (userProfileRequest.getId() == null) {
            throw new ResponseException(BadRequestError.USER_ID_CANNOT_BE_BLANK, "User id must be provided to update user profile.");
        }

        UserProfileEntity existingUserProfile = userProfileRepoService.findByUserId(userProfileRequest.getId());

        UserProfileEntity updatedUserProfile = mapper.toUpdatedEntity(userProfileRequest, existingUserProfile);

        updatedUserProfile.setId(existingUserProfile.getId());

        UserProfileEntity savedUserProfile = userProfileRepoService.save(updatedUserProfile);

        return mapper.toResponse(savedUserProfile);
    }

    @Override
    public UserProfileResponse deleteUserProfile(Long userProfileId) {
        UserProfileEntity existingUserProfile = userProfileRepoService.findByUserId(userProfileId);

        userProfileRepoService.delete(existingUserProfile);
        return mapper.toResponse(existingUserProfile);
    }

    private void handleUserProfileRequest(UserProfileRequest userProfileRequest)  {
        if (userProfileRequest.getEmail() == null || userProfileRequest.getEmail().isEmpty()) {
            throw new ResponseException(BadRequestError.USER_EMAIL_CANNOT_BE_BLANK);
        }

        // Check if the email is in correct format
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!userProfileRequest.getEmail().matches(emailRegex)) {
            throw new ResponseException(BadRequestError.USER_EMAIL_INVALID);
        }

        if (userProfileRequest.getFirstName() == null || userProfileRequest.getFirstName().isEmpty()) {
            throw new ResponseException(BadRequestError.USER_FIRST_NAME_CANNOT_BE_BLANK);
        }

        if (userProfileRequest.getLastName() == null || userProfileRequest.getLastName().isEmpty()) {
            throw new ResponseException(BadRequestError.USER_LAST_NAME_CANNOT_BE_BLANK);
        }
    }

    private void handleCreateUserProfileRequest(UserProfileRequest userProfileRequest) {
        handleUserProfileRequest(userProfileRequest);
        if (userProfileRepoService.exitsByEmail(userProfileRequest.getEmail())) {
            throw new ResponseException(BadRequestError.USER_EMAIL_ALREADY_EXISTS);
        }
    }
}
