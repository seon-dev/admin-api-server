package server.admin.model.asset.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.entity.Brand;

@Getter
@Setter
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)//null인 경우 반환할지말지 태훈님이랑 얘기해보기
public class AssetPrototypeResponse {
    private String name;
    private String decorator;
    private String code;
    private String description;
    private long releasePrice;
    private long currentPrice;
    private long pastPrice;
    private long latestBiddingPrice;
    private long yesterdayPrice;
    private String additional;
    private Integer trendy;
    private String keywords;
    private Brand brand;
    private AssetLine assetLine;
    private AssetSeason assetSeason;
    private AssetCollection assetCollection;
    private AssetBrandCategory assetBrandCategory;

    public static AssetPrototypeResponse toResponse(AssetPrototype entity){
        return AssetPrototypeResponse.builder()
                .name(entity.getName())
                .decorator(entity.getDecorator())
                .code(entity.getCode())
                .description(entity.getDescription())
                .description(entity.getDescription())
                .releasePrice(entity.getReleasePrice())
                .currentPrice(entity.getCurrentPrice())
                .pastPrice(entity.getPastPrice())
                .latestBiddingPrice(entity.getLatestBiddingPrice())
                .yesterdayPrice(entity.getYesterdayPrice())
                .additional(entity.getAdditional())
                .trendy(entity.getTrendy())
                .keywords(entity.getKeywords())
                .brand(entity.getBrand())
                .assetLine(entity.getLine())
                .assetSeason(entity.getSeason())
                .assetCollection(entity.getCollection())
                .assetBrandCategory(entity.getBrandCategory())
        .build();
    }
}
