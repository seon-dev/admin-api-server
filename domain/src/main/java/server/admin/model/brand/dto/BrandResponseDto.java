package server.admin.model.brand.dto;

import lombok.Builder;
import lombok.Data;
import server.admin.model.brand.entity.Brand;

@Data
@Builder
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


    public static BrandResponseDto ofResponse(Brand entity){
        return BrandResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .originalName(entity.getOriginalName())
                .description(entity.getDescription())
                .recommendation(entity.getRecommendation())
                .likes(entity.getLikes())
                .resource(entity.getResource())
                .resourceWallpaper(entity.getResourceWallpaper())
                .resourceCard(entity.getResourceCard())
                .color(entity.getColor())
                .build();
    }
}
