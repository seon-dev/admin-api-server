package server.admin.model.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.report.entity.AppReportCategory;

public interface AppReportCategoryRepository extends JpaRepository<AppReportCategory, Long> {
}
