package server.admin.model.asset.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.model.asset.dto.request.AssetPrototypeUpdateRequest;
import server.admin.model.brand.entity.Brand;
import server.admin.model.common.BaseTimeEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "asset_prototype")
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class AssetPrototype extends BaseTimeEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    @JsonIgnore
    private AssetSeason season;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "collection_id")
//    @JsonIgnore
//    private AssetCollection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_category_id")
    @JsonIgnore
    private AssetBrandCategory brandCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    @JsonIgnore
    private AssetLine line;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String decorator;

    @Column(length = 100, nullable = false, unique = true)
    private String code;

    @Column(length = 500)
    private String description;

    @Column(length = 500)
    private String additional;

    //TODO double type은 부동 소수점으로 오차가 존재. long type으로 고려
    //ref: https://ktko.tistory.com/entry/Effective-Java-48-%EC%A0%95%ED%99%95%ED%95%9C-%EB%8B%B5%EC%9D%B4-%ED%95%84%EC%9A%94%ED%95%98%EB%8B%A4%EB%A9%B4-float%EC%99%80-double%EC%9D%80-%ED%94%BC%ED%95%98%EB%9D%BC
    @Column(name = "release_price", precision = 17, scale = 17)
    private Long releasePrice;

    @Column(name = "current_price", precision = 17, scale = 17)
    private Long currentPrice;

    @Column(name = "past_price", precision = 17, scale = 17)
    private Long pastPrice;

    @Column(name = "latest_bidding_price", precision = 17, scale = 17)
    private Long latestBiddingPrice;

    @Column(name = "yesterday_price", precision = 17, scale = 17)
    private Long yesterdayPrice;

    @Column
    private Integer trendy;

    @Column
    private Integer likes;

    @Column(length = 500)
    private String keywords;

    @Column(name = "resource_front", length = 200)
    private String resourceFront;

    @Column(name = "resource_rear", length = 200)
    private String resourceRear;

    @Column(name = "resource_side", length = 200)
    private String resourceSide;

    @Column(name = "resource_additional", length = 200)
    private String resourceAdditional;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;
}
