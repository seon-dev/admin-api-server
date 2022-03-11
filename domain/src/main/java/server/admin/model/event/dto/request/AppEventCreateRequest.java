package server.admin.model.event.dto.request;

import com.apple.eawt.AppEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import server.admin.model.event.entity.AppEventEntity;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class AppEventCreateRequest {
    @NotNull
    private String resourceBannerUploaded;
    @NotNull
    private String resourceBannerExtension;
    @NotNull
    private String resourceContentsUploaded;
    @NotNull
    private String resourceContentsExtension;
    @NotNull
    private Timestamp startDate;
    @NotNull
    private Timestamp endDate;
    private String forwardUrl;
    private String forwardSummary;
    @NotNull
    private Boolean isEnabled;
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName(String postfix) {
        return uid + "_" + postfix + resourceBannerExtension;
    }


    public static AppEventEntity toEntityExceptResource(AppEventCreateRequest request){
        AppEventEntity appEvent = new AppEventEntity();
        appEvent.setStartTime(request.getStartDate());
        appEvent.setEndTime(request.getEndDate());
        appEvent.setForwardToUrl(request.getForwardUrl());
        appEvent.setForwardSummary(request.getForwardSummary());
        appEvent.setIsEnabled(request.getIsEnabled());
        return appEvent;

    }
}
