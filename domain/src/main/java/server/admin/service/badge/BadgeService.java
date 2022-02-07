package server.admin.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.badge.dto.BadgeResponseDto;
import server.admin.model.badge.dto.BadgeCreateUpdateDto;
import server.admin.model.badge.entity.Badge;
import server.admin.model.badge.repository.BadgeRepository;
import server.admin.model.brand.dto.BrandResponseDto;
import server.admin.model.brand.entity.Brand;
import server.admin.model.common.cursor.CursorResult;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

    private Page<Badge> getBadgesWithPage(Long cursorId, Pageable pageable){
        return cursorId == null ? badgeRepository.findAllByOrderByIdAsc(pageable): badgeRepository.findByIdGreaterThanEqualOrderByIdAsc(cursorId, pageable);
    }

    private Boolean hasNext(Long lastId) {
        if (lastId == null) return false;
        return badgeRepository.existsByIdGreaterThan(lastId);
    }

    @Transactional(readOnly = true)
    public CursorResult<BadgeResponseDto> getAllBadge(Long cursorId, Pageable pageable){
        final Page<Badge> allWithPagination = this.getBadgesWithPage(cursorId, pageable);
        final Page<BadgeResponseDto> allDtoWithPagination = new PageImpl<>(allWithPagination
                .map(BadgeResponseDto::toResponse)
                .toList());

        final List<Badge> badgeList = allWithPagination.getContent();
        final Long lastIdOfList = !allWithPagination.isEmpty() ? badgeList.get(badgeList.size()-1).getId() : null;

        return new CursorResult<>(allDtoWithPagination, hasNext(lastIdOfList));
    }

    public BadgeResponseDto getBadge(Long badgeId){
        Optional<Badge> optionalBadge = badgeRepository.findById(badgeId);
        optionalBadge.orElseThrow(()->new NoSuchElementException("해당하는 뱃지가 존재하지 않습니다."));
        return BadgeResponseDto.toResponse(optionalBadge.get());
    }

    public BadgeResponseDto updateBadge(Long badgeId, BadgeCreateUpdateDto dto){
         Optional<Badge> optionalBadge=badgeRepository.findById(badgeId);

         optionalBadge.orElseThrow(()->new NoSuchElementException("해당하는 뱃지가 존재하지 않습니다."));

         Badge badge = optionalBadge.get();
         badge.setName(dto.getName());
         badge.setDescription(dto.getDescription());
         badge.setResource(dto.getResource());

         return BadgeResponseDto.toResponse(badge);
    }

    public BadgeResponseDto saveBadge(BadgeCreateUpdateDto dto){
        return BadgeResponseDto.toResponse(badgeRepository.save(Badge.toEntity(dto)));
    }

    public void deleteBadge(Long badgeId){
        Optional<Badge> optionalBadge = badgeRepository.findById(badgeId);

        optionalBadge.orElseThrow(()-> new NoSuchElementException("해당하는 뱃지가 존재하지 않습니다."));

        badgeRepository.delete(optionalBadge.get());
    }

}
