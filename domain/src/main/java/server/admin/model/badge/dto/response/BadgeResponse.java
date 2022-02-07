package server.admin.model.badge.dto.response;

import lombok.Builder;
import server.admin.model.badge.entity.Badge;
@Builder
public class BadgeResponse {

    private Long id;
    private String name;
    private String description;
    private String resource;
    public static BadgeResponse toResponse(Badge entity){
        return BadgeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .resource(entity.getResource())
                .build();
    }
}
