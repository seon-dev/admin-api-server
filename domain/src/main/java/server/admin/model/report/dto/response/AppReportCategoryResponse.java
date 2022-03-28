package server.admin.model.report.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.report.entity.AppReport;
import server.admin.model.report.entity.AppReportCategory;

@Getter
@Builder
public class AppReportCategoryResponse {
    private Long id;
    private Long parentReportCategoryId;
    private String name;
    private Boolean isEnabled;

    public static AppReportCategoryResponse toResponse(AppReportCategory entity){
        return AppReportCategoryResponse.builder()
                .id(entity.getId())
                .parentReportCategoryId(entity.getParentReportCategoryId())
                .name(entity.getName())
                .isEnabled(entity.getIsEnabled())
                .build();

    }
}
