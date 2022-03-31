package server.admin.model.report.dto.request;

import lombok.Getter;
import server.admin.model.report.entity.AppReport;

@Getter
public class AppReportUpdateRequest {
    private AppReport.ReportStatus status;
    private Long verifierId;
    private String verifierComment;

}


