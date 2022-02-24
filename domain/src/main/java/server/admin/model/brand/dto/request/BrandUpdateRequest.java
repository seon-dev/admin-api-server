package server.admin.model.brand.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.brand.entity.Brand;

@Getter
public class BrandUpdateRequest {
    @NotNull
    private String name;
    @NotNull
    private String originalName;
    private String description;
//    private String recommendation;
//    private int likes;
    private MultipartFile resource;
    private MultipartFile resourceWallpaper;
    private MultipartFile resourceCard;
    private String color;

    public Brand toEntityExcept(Brand entity){
        if(getColor() != null) entity.setColor(this.getColor());
        if(getDescription() != null) entity.setDescription(this.getDescription());
        if(getName() != null) entity.setName(this.getName());
        if(getOriginalName() != null) entity.setOriginalName(this.getOriginalName());
        return entity;
    }
}