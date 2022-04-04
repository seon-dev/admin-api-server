package server.admin.model.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.admin.model.report.entity.AppReportProcess;

@Repository
public interface AppReportProcessRepository extends JpaRepository<AppReportProcess,Long> {

}
