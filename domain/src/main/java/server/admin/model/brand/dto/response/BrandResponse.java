package server.admin.model.brand.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.brand.entity.Brand;

import java.util.List;

@Data
@Builder
public class BrandResponse {
    private Long id;
    private String name;
    private String originalName;
    private String description;
//    private String recommendation;
    private int likes;
    private String resource;
    private String resourceWallpaper;
    private String resourceCard;
    private String color;
//    private List<AssetBrandCategory.Minified> brandCategories;
    private Boolean isEnabled;


    public static BrandResponse toResponseWithoutBrandCategory(Brand entity){
        return BrandResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .originalName(entity.getOriginalName())
                .description(entity.getDescription())
//                .recommendation(entity.getRecommendation())
                .likes(entity.getLikes())
                .resource(entity.getResource())
                .resourceWallpaper(entity.getResourceWallpaper())
                .resourceCard(entity.getResourceCard())
                .color(entity.getColor())
                .isEnabled(entity.getIsEnabled())
                .build();
    }

    @Getter
    @Builder
    public static class Minified{
        private Long id;
        private String name;
        private String originalName;
        private String resource;

        public static Minified of(Brand entity){
            return Minified.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .originalName(entity.getOriginalName())
                    .resource(entity.getResource())
                    .build();
        }

    }
}
