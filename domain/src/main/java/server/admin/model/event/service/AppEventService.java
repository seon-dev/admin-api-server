package server.admin.model.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.event.model.dto.AppEventResponse;
import server.admin.model.event.model.entity.AppEventEntity;
import server.admin.model.event.repository.AppEventRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class AppEventService {

    private final AppEventRepository repository;

    public AppEventResponse getAppEvent(Long eventId) {
        Optional<AppEventEntity> optionalEvent = repository.findById(eventId);
        if (optionalEvent.isPresent()) {
            return AppEventResponse.toResponse(optionalEvent.get());
        } else throw new NoSuchElementException("해당하는 이벤트가 존재하지 않습니다.");
    }

    public Page<AppEventResponse> getAppEvents(
            final Pageable pageable
    ) {
        return repository.findAllByIsEnabledTrueAndIsArchivedFalse(pageable).map(AppEventResponse::toResponse);
    }
}
