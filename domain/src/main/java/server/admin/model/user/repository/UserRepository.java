package server.admin.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.admin.model.user.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
