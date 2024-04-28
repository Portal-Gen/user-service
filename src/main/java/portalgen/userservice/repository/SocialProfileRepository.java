package portalgen.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portalgen.userservice.entity.SocialProfileEntity;

@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfileEntity, Long> {
}
