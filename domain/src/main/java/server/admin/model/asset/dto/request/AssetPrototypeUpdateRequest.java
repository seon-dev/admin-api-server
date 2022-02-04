package server.admin.model.asset.dto.request;

import lombok.*;
import server.admin.model.asset.entity.*;
import server.admin.model.brand.entity.Brand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
//업데이트속성들을 optional하게 requestscript 짤 수 있는지 시도
//https://kys9261.github.io/2021/10/13/programming/java/gson-exclude-field/
public class AssetPrototypeUpdateRequest {
    @NotBlank
    private String name;
    private String decorator;
    private String code;
    private String description;
    @NotNull
    private long releasePrice;
    @NotNull
    private long currentPrice;
    private long pastPrice;
    private long latestBiddingPrice;
    private long yesterdayPrice;
    private String additional;
    private Integer trendy;
    private Integer likes;
    private String resourceFront;
    private String resourceRear;
    private String resourceSide;
    private String resourceAdditional;
    private String keywords;
    @NotNull
    private Long brandId;
    private Long assetLineId;
    private Long assetSeasonId;
    private Long assetCollectionId;
    @NotNull
    private Long assetBrandCategoryId;


}
