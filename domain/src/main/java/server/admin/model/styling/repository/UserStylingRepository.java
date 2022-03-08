package server.admin.model.styling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.admin.model.styling.entity.UserStyling;
@Repository
public interface UserStylingRepository extends JpaRepository<UserStyling,Long>, UserStylingRepositoryCustom {
}
