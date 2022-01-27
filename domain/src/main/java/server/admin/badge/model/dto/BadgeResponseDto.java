package server.admin.badge.model.dto;

import lombok.Builder;
import server.admin.badge.model.entity.Badge;
@Builder
public class BadgeResponseDto {

    private Long id;
    private String name;
    private String description;
    private String resource;
    public static BadgeResponseDto toResponse(Badge entity){
        return BadgeResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .resource(entity.getResource())
                .build();
    }
}
