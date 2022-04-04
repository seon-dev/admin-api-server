package server.admin.model.report.dto.response;

import lombok.Builder;
import lombok.Getter;
import server.admin.model.report.entity.AppReportCategory;

import java.util.Optional;

@Getter
@Builder
public class AppReportCategoryResponse {
    private Long id;
    private Long parentId;
    private String name;
    private Boolean isEnabled;

    public static AppReportCategoryResponse toResponse(AppReportCategory entity){
        if (entity==null) return null;
        return AppReportCategoryResponse.builder()
                .id(entity.getId())
                .parentId(entity.getParentId())
                .name(entity.getName())
                .isEnabled(entity.getIsEnabled())
                .build();

    }
}
