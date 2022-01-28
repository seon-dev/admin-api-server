package server.admin.model.badge.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadgeCreateUpdateDto {

    private String name;

    private String description;
    private String resource;

}
