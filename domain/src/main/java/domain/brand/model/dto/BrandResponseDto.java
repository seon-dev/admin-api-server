package domain.brand.model.dto;

import domain.brand.model.entity.Brand;
import lombok.Data;

@Data
public class BrandResponseDto {
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

    public BrandResponseDto(Brand brand){
        this.id = brand.getId();
        this.name = brand.getName();
        this.originalName = brand.getOriginalName();
        this.description = brand.getDescription();
        this.recommendation = brand.getRecommendation();;
        this.likes = brand.getLikes();
        this.resource = brand.getResource();
        this.resourceWallpaper = brand.getResourceWallpaper();
        this.resourceCard = brand.getResourceCard();
        this.color = brand.getColor();
    }
}
