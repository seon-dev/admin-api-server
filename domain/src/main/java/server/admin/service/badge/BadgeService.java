package server.admin.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.badge.dto.request.BadgeCreateUpdateRequest;
import server.admin.model.badge.entity.Badge;
import server.admin.model.badge.repository.BadgeRepository;
import server.admin.model.common.cursor.CursorResult;

import java.util.List;
import java.util.Optional;

import static server.admin.model.badge.exception.BadgeException.*;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

    private Page<Badge> getBadgesWithPage(Long cursorId, Pageable pageable){
        return cursorId == null ? badgeRepository.findAllByOrderByIdAsc(pageable,true): badgeRepository.findByIdGreaterThanEqualOrderByIdAsc(cursorId, pageable,true);
    }

    private Boolean hasNext(Long lastId) {
        if (lastId == null) return false;
        return badgeRepository.existsByIdGreaterThan(lastId);
    }

//    @Transactional(readOnly = true)
//    public CursorResult<BadgeResponse> getAllBadge(Long cursorId, Pageable pageable){
//        final Page<Badge> allWithPagination = this.getBadgesWithPage(cursorId, pageable);
//        final Page<BadgeResponse> allDtoWithPagination = new PageImpl<>(allWithPagination
//                .map(BadgeResponse::toResponse)
//                .toList());
//
//        final List<Badge> badgeList = allWithPagination.getContent();
//        final Long lastIdOfList = !allWithPagination.isEmpty() ? badgeList.get(badgeList.size()-1).getId() : null;
//
//        return new CursorResult<>(allDtoWithPagination, hasNext(lastIdOfList));
//    }

    @Transactional(readOnly = true)
    public BadgeResponse getBadge(Long badgeId){
        Optional<Badge> optionalBadge = badgeRepository.findById(badgeId);
        optionalBadge.orElseThrow(BadgeNotExistException::new);
        return BadgeResponse.toResponse(optionalBadge.get());
    }

    public BadgeResponse updateBadge(Long badgeId, BadgeCreateUpdateRequest dto){
         Optional<Badge> optionalBadge=badgeRepository.findById(badgeId);

         optionalBadge.orElseThrow(BadgeNotExistException::new);

         Badge badge = optionalBadge.get();
         badge.setName(dto.getName());
         badge.setDescription(dto.getDescription());
         badge.setResource(dto.getResource());

         return BadgeResponse.toResponse(badge);
    }

    public BadgeResponse saveBadge(BadgeCreateUpdateRequest dto){
        return BadgeResponse.toResponse(badgeRepository.save(Badge.toEntity(dto)));
    }

    public void deleteBadge(Long badgeId){
        Optional<Badge> optionalBadge = badgeRepository.findById(badgeId);
        optionalBadge.orElseThrow(BadgeNotExistException::new);
        optionalBadge.ifPresentOrElse(
                badge -> { badgeRepository.delete(badge); },
                () -> {
                    throw new BadgeNotExistException();
                }
        );
    }

}
