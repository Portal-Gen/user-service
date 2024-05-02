package portalgen.userservice.service;

import portalgen.userservice.entity.UserProfileEntity;

import java.util.List;

public interface UserProfileRepoService {
    UserProfileEntity save (UserProfileEntity userProfileEntity);

    UserProfileEntity findByUserId (Long userId);

    UserProfileEntity findByEmail (String email);

    List<UserProfileEntity> findAll();

    boolean exitsByEmail(String email);

    void delete(UserProfileEntity userProfileEntity);
}
