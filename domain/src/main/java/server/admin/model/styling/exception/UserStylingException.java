package server.admin.model.styling.exception;

public class UserStylingException {

    public static class UserStylingNotExistException extends RuntimeException{
        public UserStylingNotExistException() {
            super("해당 유저 스타일링이 존재하지 않습니다.");
        }
    }

}
