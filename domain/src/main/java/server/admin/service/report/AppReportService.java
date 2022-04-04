package server.admin.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.report.dto.request.AppReportUpdateRequest;
import server.admin.model.report.dto.response.AppReportCategoryResponse;
import server.admin.model.report.dto.response.AppReportResponse;
import server.admin.model.report.entity.AppReport;
import server.admin.model.report.exception.AppReportException;
import server.admin.model.report.repository.AppReportCategoryRepository;
import server.admin.model.report.repository.AppReportRepository;
import server.admin.model.user.dto.response.UserProfileResponse;
import server.admin.model.user.exception.UserException;
import server.admin.model.user.repository.UserRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppReportService {
    private final AppReportRepository appReportRepository;
    private final UserRepository userRepository;
    private final AppReportCategoryRepository appReportCategoryRepository;

    @Transactional(readOnly = true)
    public PageResult<AppReportResponse> getAllAppReport(){
        List<AppReport> appReportList = appReportRepository.findAllFetchJoin();
        if(appReportList.isEmpty()) throw new AppReportException.AppReportNotExistException();
        List<AppReportResponse> appReportResponseList = new ArrayList<>();
        appReportList.forEach(appReport -> {
            AppReportResponse appReportResponse = AppReportResponse.toResponseExcept(appReport);
            appReportResponse.setReporter(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getReporterId()).orElseThrow(UserException.UserNotExistException::new))); //예외처리다시하기
            appReportResponse.setReportee(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getReporteeId()).orElseThrow(UserException.UserNotExistException::new)));
            appReportResponse.setVerifier(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getVerifierId()).orElse(null)));
            appReportResponse.setCategory(AppReportCategoryResponse.toResponse(appReportCategoryRepository.findById(appReport.getCategoryId()).orElse(null)));
            appReportResponseList.add(appReportResponse);
        });
        PageImpl<AppReportResponse> pageResult = new PageImpl<>(appReportResponseList, Pageable.unpaged(), appReportResponseList.size());
        return new PageResult<>(pageResult);
    }

    @Transactional(readOnly = true)
    public AppReportResponse getAppReport(Long appReportId){
        AppReport appReport = appReportRepository.findById(appReportId).orElseThrow(AppReportException.AppReportNotExistException::new);
        AppReportResponse appReportResponse = AppReportResponse.toResponseExcept(appReport);
        appReportResponse.setReporter(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getReporterId()).orElseThrow(UserException.UserNotExistException::new))); //예외처리다시하기
        appReportResponse.setReportee(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getReporteeId()).orElseThrow(UserException.UserNotExistException::new)));
        appReportResponse.setVerifier(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getVerifierId()).orElse(null)));
        appReportResponse.setCategory(AppReportCategoryResponse.toResponse(appReportCategoryRepository.findById(appReport.getCategoryId()).orElse(null)));
        return appReportResponse;
    }

    public AppReportResponse updateAppReport(Long appReportId, AppReportUpdateRequest request){
        AppReport appReport = appReportRepository.findById(appReportId).orElseThrow(AppReportException.AppReportNotExistException::new);
        appReport.setStatus(request.getStatus());
        appReport.setVerifierComment(request.getVerifierComment());
        appReport.setVerifierId(request.getVerifierId());
        AppReport savedAppReport = appReportRepository.save(appReport);
        AppReportResponse appReportResponse = AppReportResponse.toResponseExcept(savedAppReport);
        appReportResponse.setReporter(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getReporterId()).orElseThrow(UserException.UserNotExistException::new))); //예외처리다시하기
        appReportResponse.setReportee(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getReporteeId()).orElseThrow(UserException.UserNotExistException::new)));
        appReportResponse.setVerifier(UserProfileResponse.Minified.of(userRepository.findByIdAndIsEnabledTrue(appReport.getVerifierId()).orElse(null)));
        appReportResponse.setCategory(AppReportCategoryResponse.toResponse(appReportCategoryRepository.findById(appReport.getCategoryId()).orElse(null)));
        return appReportResponse;
    }


}
