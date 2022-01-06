package domain.brand.model.dto;

import lombok.Getter;

@Getter
public class BrandCreateDto {
    private Long id;
    private String name;
    private String originalName;
    private String description;
    private String recommendation;
    private int likes;
    private String resource;
    private String resourceWallpaper;
    private String resourceCard;
    private String color;
}
