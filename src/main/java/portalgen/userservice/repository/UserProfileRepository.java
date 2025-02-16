package portalgen.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import portalgen.userservice.entity.UserProfileEntity;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    Optional<UserProfileEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
