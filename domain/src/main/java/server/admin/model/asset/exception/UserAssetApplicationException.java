package server.admin.model.asset.exception;

public class UserAssetApplicationException {

    public static class UserAssetApplicationNotExistException extends RuntimeException{
        public UserAssetApplicationNotExistException() { super("해당 유저 에셋 신청 정보가 존재하지 않습니다."); }
    }
}
