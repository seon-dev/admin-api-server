package server.admin.model.brand.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BrandCreateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String originalName;
    private String description;
    private String resource;
    private String resourceWallpaper;
    private String resourceCard;
    private String color;
}
