package server.admin.model.event.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.admin.model.event.model.entity.AppEventEntity;

@Builder
@Getter
@Setter
public class AppEventResponse {
    private Long id;
    private String name;
    private String contents;
    private String resource;
    private String resourceContents;
    private String forwardToUrl;
    private String forwardSummary;

    public static AppEventResponse toResponse(AppEventEntity entity) {
        return AppEventResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .contents(entity.getContents())
                .resource(entity.getResource())
                .resourceContents(entity.getResourceContents())
                .forwardToUrl(entity.getForwardToUrl())
                .forwardSummary(entity.getForwardSummary())
                .build();
    }
}
