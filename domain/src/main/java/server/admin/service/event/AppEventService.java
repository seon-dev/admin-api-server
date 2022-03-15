package server.admin.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.event.dto.response.AppEventResponse;
import server.admin.model.event.dto.request.AppEventCreateRequest;
import server.admin.model.event.dto.request.AppEventUpdateRequest;
import server.admin.model.event.entity.AppEventEntity;
import server.admin.model.event.exception.AppEventException;
import server.admin.model.event.repository.AppEventRepository;
import server.admin.utils.S3Service;
import server.admin.utils.page.PageResult;

import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class AppEventService {
    private final S3Service s3Service;
    private final AppEventRepository repository;

    @Transactional(readOnly = true)
    public AppEventResponse getAppEvent(Long eventId) {
        Optional<AppEventEntity> optionalEvent = repository.findById(eventId);
        if (optionalEvent.isPresent()) {
            return AppEventResponse.toResponse(optionalEvent.get());
        } else throw new AppEventException.AppEventNotExistException();
    }

    @Transactional(readOnly = true)
    public PageResult<AppEventResponse> getAppEvents(
            final Pageable pageable
    ) {
        Page<AppEventResponse> appEventResponses = repository.findAll(pageable).map(AppEventResponse::toResponse);
        return new PageResult(appEventResponses);
    }

    public AppEventResponse createAppEvent(AppEventCreateRequest request) throws Exception {
        AppEventEntity appEvent = AppEventCreateRequest.toEntityExceptResource(request);

        final String filename = request.getResourceFileName("banner");
        s3Service.upload(request.getResourceBannerUploaded(), filename);
        appEvent.setResource(filename);

        final String contentFilename = request.getResourceFileName("content");
        s3Service.upload(request.getResourceContentsUploaded(), contentFilename);
        appEvent.setResourceContents(contentFilename);

        return AppEventResponse.toResponse(repository.save(appEvent));
    }

    public AppEventResponse updateAppEvent(Long appEventId, AppEventUpdateRequest request) throws Exception {
        Optional<AppEventEntity> optionalAppEvent = repository.findById(appEventId);
        AppEventEntity appEventEntity = AppEventUpdateRequest.setEntityExceptResource(optionalAppEvent.orElseThrow(AppEventException.AppEventNotExistException::new), request);
        if( request.getResourceBannerUploaded() != null && request.getResourceBannerUploaded() != null){
            final String filename = request.getResourceFileName("banner");
            s3Service.upload(request.getResourceBannerUploaded(), filename);
            appEventEntity.setResource(filename);
        }
        if( request.getResourceContentsExtension() != null && request.getResourceContentsUploaded() != null){
            final String contentFilename = request.getResourceFileName("content");
            s3Service.upload(request.getResourceBannerUploaded(), contentFilename);
            appEventEntity.setResourceContents(contentFilename);
        }



        return AppEventResponse.toResponse(appEventEntity);
    }

    public void deleteAppEvent(Long id) {
        repository.findById(id).orElseThrow(AppEventException.AppEventNotExistException::new).setIsEnabled(false);
    }
}
