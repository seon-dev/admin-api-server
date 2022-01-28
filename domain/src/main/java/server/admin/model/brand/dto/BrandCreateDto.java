package server.admin.model.brand.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
public class BrandCreateDto {
    @NotNull
    private String name;
    @NotNull
    private String originalName;
    private String description;
    private String resource;
    private String resourceWallpaper;
    private String resourceCard;
    private String color;
}
