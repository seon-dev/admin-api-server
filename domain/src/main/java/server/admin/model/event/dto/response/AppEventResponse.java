package server.admin.model.event.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.admin.model.event.entity.AppEventEntity;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
public class AppEventResponse {
    private Long id;
    private String resourceBanner;
    private String resourceContents;
    private Timestamp startDate;
    private Timestamp endDate;
    private String forwardUrl;
    private String forwardSummary;
    private Boolean isEnabled;

    public static AppEventResponse toResponse(AppEventEntity entity) {
        return AppEventResponse.builder()
                .id(entity.getId())
                .resourceBanner(entity.getResource())
                .resourceContents(entity.getResourceContents())
                .startDate(entity.getStartTime())
                .endDate(entity.getEndTime())
                .forwardUrl(entity.getForwardToUrl())
                .forwardSummary(entity.getForwardSummary())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
}
