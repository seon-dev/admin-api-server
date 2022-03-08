package server.admin.model.styling.exception;

public class UserStylingCommentException {
    public static class UserStylingCommentNotExistException extends RuntimeException {
        public UserStylingCommentNotExistException() {
            super("해당 유저 스타일링 댓글이 존재하지 않습니다.");
        }
    }
}
