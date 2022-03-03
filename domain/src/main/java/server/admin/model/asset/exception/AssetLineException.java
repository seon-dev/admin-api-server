package server.admin.model.asset.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class AssetLineException {
    public static class AssetLineNotExistException extends RuntimeException{

        public AssetLineNotExistException(){
            super("해당 에셋 라인이 존재하지 않습니다.");
        }
    }

    public static class AssetLineDuplicateException extends RuntimeException{
        public AssetLineDuplicateException() { super("해당 에셋 라인이 이미 존재합니다."); }
    }
}
