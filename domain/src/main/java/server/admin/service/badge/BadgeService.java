package server.admin.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.admin.model.badge.dto.request.BadgeCreateRequest;
import server.admin.model.badge.dto.request.BadgeUpdateRequest;
import server.admin.model.badge.dto.response.BadgeResponse;
import server.admin.model.badge.entity.Badge;
import server.admin.model.badge.repository.BadgeRepository;
import server.admin.utils.S3Service;
import server.admin.utils.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static server.admin.model.badge.exception.BadgeException.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final S3Service s3Service;

    @Transactional(readOnly = true)
    public PageResult<BadgeResponse> getAllBadge(){
        List<Badge> badgeList = badgeRepository.findAll();
        List<BadgeResponse> badgeResponseList = new ArrayList<>();
        badgeList.forEach(badge -> {
            badgeResponseList.add(BadgeResponse.toResponse(badge));
        });
        PageImpl<BadgeResponse> pageResult = new PageImpl<>(badgeResponseList, Pageable.unpaged(), badgeResponseList.size());
        return new PageResult<>(pageResult);
    }

    @Transactional(readOnly = true)
    public BadgeResponse getBadge(Long id){
        Optional<Badge> optionalBadge = badgeRepository.findById(id);
        return BadgeResponse.toResponse(optionalBadge.orElseThrow(BadgeNotExistException::new));
    }

    public BadgeResponse updateBadge(Long id, BadgeUpdateRequest request) throws Exception {
        Optional<Badge> optionalBadge = badgeRepository.findById(id);
        Badge badge = request.setEntityExcept(optionalBadge.orElseThrow(BadgeNotExistException::new));
        if( request.getResourceExtension() != null && request.getResourceUploaded() != null ){
            s3Service.upload(request.getResourceUploaded(),request.getResourceFileName());
            badge.setResource(request.getResourceFileName());
        }
        return BadgeResponse.toResponse(badge);
    }

    public BadgeResponse saveBadge(BadgeCreateRequest request) throws Exception {
        Badge badge = request.toEntityExcept();
        s3Service.upload(request.getResourceUploaded(),request.getResourceFileName());
        badge.setResource(request.getResourceFileName());
        return BadgeResponse.toResponse(badge);
    }

    public void deleteBadge(Long id){
        Optional<Badge> optionalBadge = badgeRepository.findById(id);
        optionalBadge.orElseThrow(BadgeNotExistException::new).setIsEnabled(false);
    }

}
