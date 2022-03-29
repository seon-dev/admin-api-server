package server.admin.model.report.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import server.admin.model.report.entity.AppReport;

@Getter
//@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(using = UpdateRequestDeserializer.class)
public class AppReportUpdateRequest {
    private AppReport.ReportStatus status;
    private Long verifierId;
    private String verifierComment;

}


