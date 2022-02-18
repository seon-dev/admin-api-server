package server.admin.user.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import server.admin.common.exception.dto.ErrorResponse;
import server.admin.model.common.rest.RestFailResponse;
import server.admin.model.user.exception.UserPolicyAgreementException;

import static server.admin.model.user.exception.UserPolicyAgreementException.*;

public class UserPolicyAgreementHandler {
    @Getter
    @RequiredArgsConstructor
    public enum UserPolicyAgreementErrorCode{
        USER_POLICY_AGREEMENT_NOT_EXIST_CODE(404, "해당 유저는 개인 정보 약관 동의가 필요합니다.");

        private final int statusCode;
        private final String description;
    }

    @ExceptionHandler(UserPolicyAgreementNotExistException.class)
    public RestFailResponse UserPolicyAgreementNotExistHandler(UserPolicyAgreementNotExistException exception){
        return new RestFailResponse(UserPolicyAgreementErrorCode.USER_POLICY_AGREEMENT_NOT_EXIST_CODE.getStatusCode(), exception.getMessage());
    }

}
