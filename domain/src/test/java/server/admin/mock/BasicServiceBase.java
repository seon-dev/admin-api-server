//package server.admin.mock;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import server.admin.model.asset.entity.Asset;
//import server.admin.model.asset.entity.AssetBrandCategory;
//import server.admin.model.asset.entity.AssetCategory;
//import server.admin.model.asset.entity.AssetPrototype;
//import server.admin.model.brand.entity.Brand;
//import server.admin.model.user.entity.User;
//
//public class BasicServiceBase {
//    final Long userId = 52L;
//    final Long verifiedId = 53L;
//    final Long assetId = 100L;
//    final Long verifiedAssetId = 101L;
//    final Long assetPrototypeId = 10L;
//    final Long assetQualityId = 10L;
//    final Long brandId = 100L;
//    final Long assetSeasonId = 100L;
//    final Long brandCategoryId = 100L;
//    final Long assetCategoryId = 100L;
//
//    public User createMockUser(Long userId){
//        final User user = new User(
//                userId,
//                "name52",
//                "nickname52",
//                true,
//                52,
//                52,
//                "resource52",
//                null,
//                "01052525252",
//                "profilelink52",
//                "introduce52",
//                "instagram52",
//                null,
//                null,
//                null,
//                null
//        );
//        return user;
//    }
//
//    public Asset createMockAsset(Long assetId){
//        final Asset asset = new Asset(
//                assetId,
//                createMockAssetPrototype(assetPrototypeId),
//                null
//        );
//        return asset;
//    }
//
//    public AssetPrototype createMockAssetPrototype(Long assetPrototypeId){
//        final AssetPrototype assetPrototype = new AssetPrototype(
//                assetPrototypeId,
//                createMockBrand(brandId),
//                null,
//                null,
//                createMockBrandCategory(brandCategoryId),
//                null,
//                "name",
//                "decorator",
//                "code",
//                "description",
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                10,
//                10,
//                "keywords",
//                "resourceFront",
//                "resourceRear",
//                "resourceSide",
//                null,
//                true
//                );
//        return assetPrototype;
//
//    }
//
//    public Brand createMockBrand(Long brandId){
//        final Brand brand = new Brand(
//                brandId,
//                "brand_name",
//                "brand_original_name",
//                "description",
//                null,
//                10,
//                "resource",
//                "resourceWallPaper",
//                "resourceCard",
//                "color",
//                true
//        );
//        return brand;
//
//    }
//
//    public AssetBrandCategory createMockBrandCategory(Long brandCategoryId){
//        final AssetBrandCategory brandCategory = new AssetBrandCategory(
//                brandCategoryId,
//                createMockBrand(brandId),
//                createMockAssetCategory(assetCategoryId),
//                true
//        );
//        return brandCategory;
//    }
//
//    public AssetCategory createMockAssetCategory(Long assetCategoryId){
//        final AssetCategory assetCategory = new AssetCategory(
//                assetCategoryId,
//                "name",
//                "resource",
//                true
//        );
//        return assetCategory;
//    }
//}
