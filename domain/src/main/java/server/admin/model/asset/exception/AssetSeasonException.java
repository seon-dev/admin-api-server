package server.admin.model.asset.exception;

public class AssetSeasonException {

    public static class AssetSeasonNotExistException extends RuntimeException{

        public AssetSeasonNotExistException(){
            super("해당 에셋 시즌이 존재하지 않습니다.");
        }
    }

}
