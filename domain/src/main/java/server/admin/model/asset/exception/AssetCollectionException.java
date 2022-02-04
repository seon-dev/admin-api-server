package server.admin.model.asset.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class AssetCollectionException {

    public static class AssetCollectionNotExistException extends RuntimeException{

        public AssetCollectionNotExistException(){
            super("해당 에셋 컬렉션이 존재하지 않습니다.");
        }
    }
}
