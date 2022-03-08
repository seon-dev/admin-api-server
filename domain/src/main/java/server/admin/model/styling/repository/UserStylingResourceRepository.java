package server.admin.model.styling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.styling.entity.UserStylingResource;

public interface UserStylingResourceRepository extends JpaRepository<UserStylingResource,Long>, UserStylingResourceRepositoryCustom {
}
