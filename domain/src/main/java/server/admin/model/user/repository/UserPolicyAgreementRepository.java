package server.admin.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.user.entity.User;
import server.admin.model.user.entity.UserPolicyAgreement;

import java.util.Optional;

public interface UserPolicyAgreementRepository extends JpaRepository<UserPolicyAgreement, Long> {
    Optional<UserPolicyAgreement> findByUserId(Long userId);
}
