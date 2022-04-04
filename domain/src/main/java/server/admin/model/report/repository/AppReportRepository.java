package server.admin.model.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.admin.model.report.entity.AppReport;

public interface AppReportRepository extends JpaRepository<AppReport, Long>, AppReportRepositoryCustom {

}
