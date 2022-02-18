package server.admin.auth.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.admin.common.exception.dto.ErrorResponse;
import server.admin.model.admin.exception.AdminException;
import server.admin.model.auth.exception.AuthException;
import server.admin.model.common.rest.RestFailResponse;

import static server.admin.model.auth.exception.AuthException.*;

@RestControllerAdvice
public class AuthHandler {
    @Getter
    @RequiredArgsConstructor
    public enum AuthErrorCode{
        INVALID_EMAIL_ADDRESS_CODE(404, "인증되지 않은 이메일 입니다. 이메일 인증을 먼저 해주세요."), //SMTP 553 if the email address you send to does not exist,
        INVALID_EMAIL_VERIFICATION_CODE(404,"유효한 인증번호가 아닙니다."),
        INVALID_NAME_CODE(400,"관리자 이름은 한글만 가능합니다."),
        INVALID_PASSWORD_CODE(400,"영문자, 숫자, 특수문자 포함 8자 이상만 가능합니다."),
        SIGN_IN_FAIL_CODE(400, "로그인에 실패했습니다."),
        ACCESS_DENIED_CODE(403, "접근 권한이 없습니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(InvalidEmailAddressException.class)
    public ErrorResponse InvalidEmailAddressHandler(InvalidEmailAddressException exception){
        return new ErrorResponse(AuthErrorCode.INVALID_EMAIL_ADDRESS_CODE.getStatusCode(), exception.getMessage(), HttpStatus.NOT_FOUND);//코드 수정하기
    }

    @ExceptionHandler(InvalidEmailVerificationCodeException.class)
    public ErrorResponse InvalidEmailVerificationHandler(InvalidEmailVerificationCodeException exception){
        return new ErrorResponse(AuthErrorCode.INVALID_EMAIL_VERIFICATION_CODE.getStatusCode(), exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidNameException.class)
    public ErrorResponse InvalidNameHandler(InvalidNameException exception){
        return new ErrorResponse(AuthErrorCode.INVALID_NAME_CODE.getStatusCode(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ErrorResponse InvalidPasswordHandler(InvalidPasswordException exception){
        return new ErrorResponse(AuthErrorCode.INVALID_PASSWORD_CODE.getStatusCode(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignInFailException.class)
    public RestFailResponse SignInFailHandler(SignInFailException exception){
        return new RestFailResponse(AuthErrorCode.SIGN_IN_FAIL_CODE.getStatusCode(), exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public RestFailResponse AccessDeniedHandler(AccessDeniedException exception){
        return new RestFailResponse(AuthErrorCode.ACCESS_DENIED_CODE.getStatusCode(), exception.getMessage());
    }
}
