package server.admin.model.report.repository;

import server.admin.model.report.entity.AppReport;

import java.util.List;

public interface AppReportRepositoryCustom {
    List<AppReport> findAllFetchJoin();
}
