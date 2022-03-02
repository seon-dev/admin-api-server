package server.admin.model.asset.exception;

public class AssetQualityException {
    public static class AssetQualityNotExistException extends RuntimeException{
        public AssetQualityNotExistException(){ super("해당 에셋 등급이 존재하지 않습니다."); }
    }
}
