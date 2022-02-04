package server.admin.model.asset.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class AssetBrandCategoryException {

    public static class AssetBrandCategoryNotExistException extends RuntimeException{
        public AssetBrandCategoryNotExistException(){
            super("해당 에셋 브랜드 카테고리가 존재하지 않습니다.");
        }
    }
}
