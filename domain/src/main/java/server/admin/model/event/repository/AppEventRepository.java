package server.admin.model.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.event.entity.AppEventEntity;

public interface AppEventRepository extends JpaRepository<AppEventEntity, Long> {
    Page<AppEventEntity> findAllByIsEnabledTrueAndIsArchivedFalse(Pageable pageable);

}
