package server.admin.brand.model.entity;

import server.admin.common.BaseTimeEntity;
import server.admin.brand.model.dto.BrandCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Brand extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "original_name")
    private String originalName;
    @Column
    private String description;
    @Column
    private String recommendation;
    @Column
    private int likes;
    @Column
    private String resource;
    @Column(name = "resource_wallpaper")
    private String resourceWallpaper;
    @Column(name = "resource_card")
    private String resourceCard;
    @Column
    private String color;
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    public Brand(BrandCreateDto brandCreateDto){
        this.description = brandCreateDto.getDescription();
        this.resource = brandCreateDto.getResource();
        this.name = brandCreateDto.getName();
        this.originalName = brandCreateDto.getOriginalName();
        this.color = brandCreateDto.getColor();
        this.likes = brandCreateDto.getLikes();
        this.resourceWallpaper = brandCreateDto.getResourceWallpaper();
        this.resourceCard = brandCreateDto.getResourceCard();
        this.isEnabled = true;
        this.recommendation = brandCreateDto.getRecommendation();
        this.id = brandCreateDto.getId();
    }
}
