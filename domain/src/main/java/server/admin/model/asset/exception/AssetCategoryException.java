package server.admin.model.asset.exception;

public class AssetCategoryException {

    public static class AssetCategoryNotExistException extends RuntimeException{
        public AssetCategoryNotExistException(){
            super("해당 에셋 카테고리가 존재하지 않습니다.");
        }
    }

    public static class AssetCategoryDuplicateException extends RuntimeException{
        public AssetCategoryDuplicateException(){ super("해당 에셋 카테고리가 이미 존재합니다."); }
    }
}
