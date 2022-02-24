package server.admin.model.asset.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.lang.Nullable;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.dto.response.BrandResponse;
import server.admin.model.brand.entity.Brand;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetPrototypeResponse {
    private Long id;
    private BrandResponse.Minified brand;
    private AssetLineResponse.Minified line;
    private AssetSeasonResponse season;
    private AssetBrandCategoryResponse.Minified category;
    private String name;
    private String decorator;
    private String code;
    private String description;
    private Long releasePrice;
    private String additional;
    private Integer trendy;
    private Integer likes;
    private String keywords;
    private String resourceFront;
    private String resourceAdditional;
    private String resourceRear;
    private String resourceSide;
    private Boolean isEnabled;


//    @QueryProjection
//    public AssetPrototypeResponse(Long id,BrandResponse.Minified brand, AssetLineResponse.Minified line, AssetSeasonResponse season, AssetBrandCategoryResponse.Minified category,String name,
//                                  String decorator,String code, String description, Long releasePrice, String additional, Integer trendy, Integer likes,
//                                  String keywords, String resourceFront, String resourceAdditional, String resourceRear, String resourceSide,
//                                  Boolean isEnabled
//    ){
//        this.id= id;
//        this.brand = brand;
//        this.line = line;
//        this.season = season;
//        this.category = category;
//        this.name = name;
//        this.decorator = decorator;
//        this.code = code;
//        this.description = description;
//        this.releasePrice = releasePrice;
//        this.additional = additional;
//        this.trendy = trendy;
//        this.likes = likes;
//        this.keywords = keywords;
//        this.resourceFront = resourceFront;
//        this.resourceAdditional = resourceAdditional;
//        this.resourceRear = resourceRear;
//        this.resourceSide = resourceSide;
//        this.isEnabled = isEnabled;
//    }

    public static AssetPrototypeResponse toResponse(AssetPrototype entity) {
        final BrandResponse.Minified brand = entity.getBrand() != null ? BrandResponse.Minified.of(entity.getBrand()) : null;
        final AssetLineResponse.Minified assetLine = entity.getLine() != null ? AssetLineResponse.Minified.of(entity.getLine()) : null;
        final AssetSeasonResponse assetSeason = entity.getSeason() != null ? AssetSeasonResponse.toResponse(entity.getSeason()) : null;
        final AssetBrandCategoryResponse.Minified brandCategory = entity.getBrandCategory() != null ? AssetBrandCategoryResponse.Minified.of(entity.getBrandCategory()) : null;
        return AssetPrototypeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .decorator(entity.getDecorator())
                .code(entity.getCode())
                .description(entity.getDescription())
                .description(entity.getDescription())
                .releasePrice(entity.getReleasePrice())
                .additional(entity.getAdditional())
                .trendy(entity.getTrendy())
                .keywords(entity.getKeywords())
                .brand(brand)
                .line(assetLine)
                .season(assetSeason)
                .category(brandCategory)
                .resourceFront(entity.getResourceFront())
                .resourceAdditional(entity.getResourceAdditional())
                .resourceRear(entity.getResourceRear())
                .resourceSide(entity.getResourceSide())
                .isEnabled(entity.getIsEnabled())
                .likes(entity.getLikes())
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Minified{
        private Long id;
        private String name;
        private String code;
        private BrandResponse.Minified brand;
        private Long releasePrice;
        private Integer trendy;
        private Integer likes;
        private Boolean isEnabled;
        private String resource;

        public static Minified of(AssetPrototype entity){
            return Minified.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .code(entity.getCode())
                    .brand(BrandResponse.Minified.of(entity.getBrand()))
                    .releasePrice(entity.getReleasePrice())
                    .trendy(entity.getTrendy())
                    .likes(entity.getLikes())
                    .isEnabled(entity.getIsEnabled())
                    .resource(entity.getResourceFront())
                    .build();
        }
    }



}
