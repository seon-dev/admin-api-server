package server.admin.model.asset.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import server.admin.model.asset.entity.AssetBrandCategory;
import server.admin.model.asset.entity.AssetCategory;
import server.admin.model.asset.entity.AssetLine;
import server.admin.model.brand.dto.response.BrandResponse;

import java.util.List;
@Getter
@Builder
@AllArgsConstructor
public class AssetBrandCategoryResponse {
    private Long id;
    private BrandResponse.Minified brand;
    private AssetCategoryResponse category;
    private List<AssetLineResponse> lines;
    //repositoryDI하고 set하기불러오기
    private String name;
    private Boolean isEnabled;

    public static AssetBrandCategoryResponse toResponseWithoutAssetLine(AssetBrandCategory entity){
        return AssetBrandCategoryResponse.builder()
                .id(entity.getId())
                .brand(BrandResponse.Minified.of(entity.getBrand()))
                .category(AssetCategoryResponse.toResponse(entity.getCategory()))
                .name(entity.getCategory().getName())
                .isEnabled(entity.getIsEnabled())
                .build();
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Minified{
        private Long id;
        private Long brandId;
        private Long categoryId;
        private String name;
        private Boolean isEnabled;

        public static  Minified of(AssetBrandCategory entity){
            return Minified.builder()
                    .id(entity.getId())
                    .brandId(entity.getBrand().getId())
                    .categoryId(entity.getCategory().getId())
                    .name(entity.getCategory().getName())
                    .isEnabled(entity.getIsEnabled())
                    .build();

        }
    }
}
