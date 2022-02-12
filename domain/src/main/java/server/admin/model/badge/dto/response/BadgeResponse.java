package server.admin.model.badge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.admin.model.badge.entity.Badge;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BadgeResponse {
    private Long id;
    private String name;
    private String description;
    private String resource;
    private Boolean isEnabled;

    public static BadgeResponse toResponse(Badge entity){
        return BadgeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .resource(entity.getResource())
//                .isEnabled(entity)
                .build();
    }
}
