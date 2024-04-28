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
import portalgen.userservice.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper mapper;
    @Autowired
    public UserProfileServiceImpl(UserProfileMapper mapper, UserProfileRepository repository) {
        this.mapper = mapper;
        this.userProfileRepository = repository;
    }
    @Override
    public UserProfileResponse createUserProfile(UserProfileRequest userProfileRequest)
    {
        UserProfileEntity entity = mapper.toEntity(userProfileRequest);
        UserProfileEntity savedEntity = userProfileRepository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Override
    public UserProfileResponse getUserProfile(Long userProfileId) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(userProfileId).orElseThrow(() -> new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND));
        return mapper.toResponse(userProfileEntity);
    }

    @Override
    public UserProfileResponse updateUserProfile(UpdateUserProfileRequest userProfileRequest) {
        Long userProfileId = userProfileRequest.getId();
        UserProfileEntity existingUserProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND));

        UserProfileEntity updatedUserProfile = mapper.toEntity(userProfileRequest);
        updatedUserProfile.setId(existingUserProfile.getId());
        UserProfileEntity savedUserProfile = userProfileRepository.save(updatedUserProfile);
        return mapper.toResponse(savedUserProfile);
    }

    @Override
    public UserProfileResponse deleteUserProfile(Long userProfileId) {
        UserProfileEntity existingUserProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND));
        userProfileRepository.delete(existingUserProfile);
        return mapper.toResponse(existingUserProfile);
    }
}
