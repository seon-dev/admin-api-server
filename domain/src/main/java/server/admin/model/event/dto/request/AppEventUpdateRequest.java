package server.admin.model.event.dto.request;

import com.apple.eawt.AppEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import server.admin.model.event.entity.AppEventEntity;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class AppEventUpdateRequest {
    private String resourceBannerUploaded;
    private String resourceBannerExtension;
    private String resourceContentsUploaded;
    private String resourceContentsExtension;
    private Timestamp startDate;
    private Timestamp endDate;
    private String forwardUrl;
    private String forwardSummary;
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName(String postfix) {
        return uid + "_" + postfix + resourceBannerExtension;
    }

    public static AppEventEntity setEntityExceptResource(AppEventEntity entity, AppEventUpdateRequest request){
        entity.setStartTime(request.getStartDate());
        entity.setEndTime(request.getEndDate());
        entity.setForwardSummary(request.getForwardSummary());
        entity.setForwardToUrl(request.getForwardUrl());
        return entity;
    }
}
