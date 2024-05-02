package portalgen.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portalgen.userservice.entity.UserProfileEntity;
import portalgen.userservice.exception.BadRequestError;
import portalgen.userservice.exception.ResponseException;
import portalgen.userservice.repository.UserProfileRepository;
import portalgen.userservice.service.UserProfileRepoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileRepoServiceImpl implements UserProfileRepoService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileEntity save(UserProfileEntity userProfileEntity) {
        return userProfileRepository.save(userProfileEntity);
    }

    @Override
    public UserProfileEntity findByUserId(Long userId) {
        if (userId == null) {
            throw new ResponseException(BadRequestError.USER_ID_CANNOT_BE_BLANK);
        }
        Optional<UserProfileEntity> userProfileEntity = userProfileRepository.findById(userId);
        if (userProfileEntity.isPresent()) {
            return userProfileEntity.get();
        } else {
            throw new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND, "User profile with id " + userId + " not found.");
        }
    }

    @Override
    public UserProfileEntity findByEmail(String email) {
         if (email == null) {
             throw new ResponseException(BadRequestError.USER_EMAIL_CANNOT_BE_BLANK);
         }

         Optional<UserProfileEntity> userProfileEntity = userProfileRepository.findByEmail(email);
         if (userProfileEntity.isPresent()) {
             return userProfileEntity.get();
         } else {
             throw new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND, "User profile with email " + email + " not found.");
         }
    }

    @Override
    public List<UserProfileEntity> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public boolean exitsByEmail(String email) {
        return userProfileRepository.existsByEmail(email);
    }

    @Override
    public void delete(UserProfileEntity userProfileEntity) {
        userProfileRepository.delete(userProfileEntity);
    }
}
