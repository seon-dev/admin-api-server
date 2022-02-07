package server.admin.model.brand.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BrandCreateRequest {
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
