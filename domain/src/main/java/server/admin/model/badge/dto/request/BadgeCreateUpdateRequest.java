package server.admin.model.badge.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadgeCreateUpdateRequest {

    private String name;

    private String description;
    private String resource;

}
