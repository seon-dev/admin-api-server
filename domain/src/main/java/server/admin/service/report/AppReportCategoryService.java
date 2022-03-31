package server.admin.service.report;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.report.dto.response.AppReportCategoryResponse;
import server.admin.model.report.entity.AppReportCategory;
import server.admin.model.report.exception.AppReportCategoryException;
import server.admin.model.report.repository.AppReportCategoryRepository;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppReportCategoryService {
    private final AppReportCategoryRepository appReportCategoryRepository;

    public PageResult<AppReportCategoryResponse> getAllAppReportCategory(){
        List<AppReportCategory> appReportCategoryList = appReportCategoryRepository.findAll();

        List<AppReportCategoryResponse> appReportCategoryResponses = new ArrayList<>();
        appReportCategoryList.forEach(appReportCategory -> {
            appReportCategoryResponses.add(AppReportCategoryResponse.toResponse(appReportCategory));
        });
        PageImpl<AppReportCategoryResponse> pageResult = new PageImpl<>(appReportCategoryResponses, Pageable.unpaged(), appReportCategoryResponses.size());
        return new PageResult<>(pageResult);
    }

    public AppReportCategoryResponse getAppReportCategory(Long appReportCategoryId){
        AppReportCategory appReportCategory = appReportCategoryRepository.findById(appReportCategoryId).orElseThrow(AppReportCategoryException.AppReportCategoryNotExistException::new);
        return AppReportCategoryResponse.toResponse(appReportCategory);

    }
}
