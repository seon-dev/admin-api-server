//package server.admin.model.admin.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import server.admin.model.admin.entity.Admin;
//
//import java.util.Optional;
//
//public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Optional<Admin> findByEmailVerificationCode(Integer emailVerificationCode);
//    Optional<Admin> findByEmail(String email);
//    Optional<Admin> findByIdAndIsEnabledTrue(Long id);
//    Optional<Admin> findByNameAndId(String name, Long id);
//    Optional<Admin> findByName(String name);
//}
