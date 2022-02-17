package server.admin.model.common.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class RestFailResponse<T> extends RestResponse<T> {
    String reason;

    public RestFailResponse(int code, String reason){
        this.code = code;
        this.reason = reason;
    }
    public static <T> RestFailResponse<T> newInstance(HttpStatus status, String reason){
        return new RestFailResponse<>(status.value(), reason);
    }
}
