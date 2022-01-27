package server.admin.brand.model.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import server.admin.common.BaseTimeEntity;
import server.admin.brand.model.dto.BrandCreateDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "brand")
@DynamicInsert
@DynamicUpdate
public class Brand extends BaseTimeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @Column
    @NotBlank
    private String name;
    @Column(name = "original_name")
    @NotBlank
    private String originalName;
    @Column
    @NotBlank
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
    @NotNull
    private Boolean isEnabled;


    public static Brand toEntity(final BrandCreateDto brandCreateDto){
        final Brand entity = new Brand();
        entity.setColor(brandCreateDto.getColor());
        entity.setDescription(brandCreateDto.getDescription());
        entity.setIsEnabled(true);
        entity.setName(brandCreateDto.getName());
        entity.setResourceCard(brandCreateDto.getResourceCard());
        entity.setResource(brandCreateDto.getResource());
        entity.setResourceWallpaper(brandCreateDto.getResourceWallpaper());
        entity.setOriginalName(brandCreateDto.getOriginalName());
        return entity;
    }
}
