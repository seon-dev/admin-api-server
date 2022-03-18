package server.admin.model.report.dto.request;

import lombok.Getter;
import server.admin.model.report.entity.ReportStatus;
@Getter
public class AppReportUpdateRequest {
    private ReportStatus status;

}
