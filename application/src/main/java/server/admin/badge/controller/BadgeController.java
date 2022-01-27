package server.admin.badge.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.admin.badge.model.dto.BadgeCreateUpdateDto;
import server.admin.badge.model.dto.BadgeResponseDto;
import server.admin.badge.model.service.BadgeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/badge")
public class BadgeController {
    private final BadgeService badgeService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("뱃지 전체 항목 조회")
    public List<BadgeResponseDto> getBadges(){
        return this.badgeService.getAllBadge();
    }

    @GetMapping("/{badgeId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("뱃지 단일 항목 조회")
    public BadgeResponseDto getBadge(@PathVariable("badgeId") Long badgeId){
        return this.badgeService.getBadge(badgeId);
    }

    @PutMapping("/{badgeId}/badge")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("뱃지 업데이트")
    public BadgeResponseDto updateBadge(
            @PathVariable("badgeId") Long badgeId,
            @RequestBody BadgeCreateUpdateDto dto
    ){
        return this.badgeService.updateBadge(badgeId,dto);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("뱃지 생성")
    public BadgeResponseDto saveBadge(@RequestBody BadgeCreateUpdateDto dto){
        return this.badgeService.saveBadge(dto);
    }


}
