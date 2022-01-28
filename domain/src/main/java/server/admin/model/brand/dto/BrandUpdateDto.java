package server.admin.model.brand.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class BrandUpdateDto {
    @NotNull
    private String name;
    @NotNull
    private String originalName;
    private String description;
    private String recommendation;
    private int likes;
    private String resource;
    private String resourceWallpaper;
    private String resourceCard;
    private String color;

}
