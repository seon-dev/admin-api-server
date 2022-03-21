package server.admin.model.report.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.report.entity.AppUserReport;
import server.admin.model.report.entity.ReportStatus;

@Getter
@Builder
public class AppUserReportResponse {
    private Long id;
    private String content;
    private String resource;
    private ReportStatus status;
    private Boolean isEnabled;

    public static AppUserReportResponse toResponse(AppUserReport entity){
        return AppUserReportResponse.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .resource(entity.getResource())
                .status(entity.getStatus())
                .isEnabled(entity.getIsEnabled())
                .build();
    }

}
