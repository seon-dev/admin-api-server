package server.admin.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.report.dto.request.AppReportUpdateRequest;
import server.admin.model.report.dto.response.AppReportResponse;
import server.admin.model.report.entity.AppReport;
import server.admin.model.report.exception.AppReportException;
import server.admin.model.report.repository.AppReportRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AppReportService {
    private final AppReportRepository appReportRepository;

    public PageResult<AppReportResponse> getAllAppReport(){
        List<AppReport> appReportList = appReportRepository.findAll();
        List<AppReportResponse> appReportResponseList = new ArrayList<>();
        appReportList.forEach(appReport -> {
            appReportResponseList.add(AppReportResponse.toResponse(appReport));
        });
        PageImpl<AppReportResponse> pageResult = new PageImpl<>(appReportResponseList, Pageable.unpaged(), appReportResponseList.size());
        return new PageResult<>(pageResult);
    }

    public AppReportResponse getAppReport(Long appReportId){
        Optional<AppReport> optionalAppReport = appReportRepository.findById(appReportId);
        return AppReportResponse.toResponse(optionalAppReport.orElseThrow(AppReportException.AppReportNotExistException::new));
    }

    public AppReportResponse updateAppReport(Long appReportId, AppReportUpdateRequest request){
        AppReport appReport = appReportRepository.findById(appReportId).orElseThrow(AppReportException.AppReportNotExistException::new);
        appReport.setStatus(request.getStatus());
        appReport.setVerifierComment(request.getVerifierComment());
        appReport.setVerifierId(request.getVerifierId());
        return AppReportResponse.toResponse(appReportRepository.save(appReport));
    }


}
