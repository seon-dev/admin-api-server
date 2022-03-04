package server.admin.model.brand.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import server.admin.model.brand.entity.Brand;

import java.util.UUID;

@Getter
public class BrandUpdateRequest {
    @NotNull
    private String name;
    @NotNull
    private String originalName;
    private String description;
//    private String recommendation;
//    private int likes;
    private String resourceUploaded;
    private String resourceExtension;

    private String resourceWallpaperUploaded;
    private String resourceWallpaperExtension;

    private String resourceCardUploaded;
    private String resourceCardExtension;

    private String color;
    @JsonIgnore
    private UUID uid = UUID.randomUUID();
    @JsonIgnore
    public String getResourceFileName(String postfix) {
        return uid + "_" + postfix + resourceExtension;
    }

    public static Brand setEntityExcept(Brand entity, BrandUpdateRequest request){
        if(request.getColor() != null) entity.setColor(request.getColor());
        if(request.getDescription() != null) entity.setDescription(request.getDescription());
        if(request.getName() != null) entity.setName(request.getName());
        if(request.getOriginalName() != null) entity.setOriginalName(request.getOriginalName());
        return entity;
    }
}