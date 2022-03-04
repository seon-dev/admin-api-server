package server.admin.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.admin.model.user.entity.User;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByNicknameAndPhoneNumber(String nickname, String phoneNumber);
    Optional<User> findByPhoneNumberAndIsEnabledTrue(String phoneNumber);
    Optional<User> findByPhoneNumberAndLoginVerificationCodeAndLoginVerificationExpiredAtIsAfterAndIsEnabledTrue(String phoneNumber, Integer verificationCode, Timestamp timestamp);
    Optional<User> findByIdAndIsEnabledTrue(Long id);
    Optional<User> findByIdAndRefreshToken(Long id, String refreshToken);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
