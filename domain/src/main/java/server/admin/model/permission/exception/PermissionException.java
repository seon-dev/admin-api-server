package server.admin.model.permission.exception;

public class PermissionException {
    public static class PermissionNotExistException extends RuntimeException{
        public PermissionNotExistException(){
            super("해당 레벨 권한이 존재하지 않습니다.");
        }
    }
}
