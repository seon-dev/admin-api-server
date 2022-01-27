package server.admin.badge.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import server.admin.badge.model.dto.BadgeCreateUpdateDto;
import server.admin.badge.model.dto.BadgeResponseDto;
import server.admin.badge.model.entity.Badge;
import server.admin.badge.model.repository.BadgeRepository;
import server.admin.brand.model.entity.Brand;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

    public BadgeResponseDto getBadge(Long badgeId){
        Optional<Badge> optionalBadge = badgeRepository.findById(badgeId);
        optionalBadge.orElseThrow(()->new NoSuchElementException("해당하는 뱃지가 존재하지 않습니다."));
        return BadgeResponseDto.toResponse(optionalBadge.get());
    }

    public List<BadgeResponseDto> getAllBadge(){
        List<Badge> all = badgeRepository.findAll();
        List<BadgeResponseDto> dtoList = new ArrayList<>();
        all.forEach(badge -> dtoList.add(BadgeResponseDto.toResponse(badge)));
        return dtoList;
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
