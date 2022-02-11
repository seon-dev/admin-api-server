package server.admin.model.asset.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.entity.Brand;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetPrototypeResponse {
    private Long id;
    private Long brandId; //brandminified로 변경하기
    private Long assetLineId;
    private Long assetSeasonId;
    //    private AssetCollection assetCollection; 삭제한다고함
    private Long assetBrandCategoryId;
    private String name;
    private String decorator;
    private String code;
    private String description;
    private long releasePrice;
//    private long currentPrice;
//    private long pastPrice;
//    private long latestBiddingPrice;
//    private long yesterdayPrice;
    private String additional;
    private Integer trendy;
    private String keywords;
    private String resourceFront;
    private String resourceAdditional;
    private String resourceRear;
    private String resourceSide;
    private Boolean isEnabled;


    public static AssetPrototypeResponse toResponse(AssetPrototype entity){
        final Long assetLineId = entity.getLine() != null? entity.getLine().getId() : null;
        final Long brandId = entity.getBrand() != null? entity.getBrand().getId() : null;
        final Long assetSeasonId = entity.getSeason() != null? entity.getSeason().getId() : null;
        final Long assetBrandCategoryId = entity.getBrandCategory() != null? entity.getBrandCategory().getId() : null;
        return AssetPrototypeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .decorator(entity.getDecorator())
                .code(entity.getCode())
                .description(entity.getDescription())
                .description(entity.getDescription())
                .releasePrice(entity.getReleasePrice())
//                .currentPrice(entity.getCurrentPrice())
//                .pastPrice(entity.getPastPrice())
//                .latestBiddingPrice(entity.getLatestBiddingPrice())
//                .yesterdayPrice(entity.getYesterdayPrice())
                .additional(entity.getAdditional())
                .trendy(entity.getTrendy())
                .keywords(entity.getKeywords())
                .brandId(brandId)
                .assetLineId(assetLineId)
                .assetSeasonId(assetSeasonId)
                .assetBrandCategoryId(assetBrandCategoryId)
                .resourceFront(entity.getResourceFront())
                .resourceAdditional(entity.getResourceAdditional())
                .resourceRear(entity.getResourceRear())
                .resourceSide(entity.getResourceSide())
                .isEnabled(entity.getIsEnabled())
//                .assetCollection(entity.getCollection())
        .build();
    }

}
