package server.admin.model.brand.dto.response;

import lombok.*;
import server.admin.model.asset.dto.response.AssetBrandCategoryResponse;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.brand.entity.Brand;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private List<AssetBrandCategoryResponse.Minified> brandCategories;
    private Boolean isEnabled;
    private Integer numberOfAssets;
    private Integer numberOfStylings;


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
                .numberOfAssets(entity.getNumberOfAssets())
                .numberOfStylings(entity.getNumberOfStylings())
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
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
