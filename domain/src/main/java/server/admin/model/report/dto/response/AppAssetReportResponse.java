package server.admin.model.report.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.report.entity.AppAssetReport;
import server.admin.model.report.entity.ReportStatus;
@Getter
@Builder
public class AppAssetReportResponse {
    private Long id;
    private String brand;
    private String lineOrName;
    private ReportStatus status;
    private Boolean isEnabled;

    public static AppAssetReportResponse toResponse(AppAssetReport entity){
        return AppAssetReportResponse.builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .lineOrName(entity.getLineOrName())
                .status(entity.getStatus())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
}
