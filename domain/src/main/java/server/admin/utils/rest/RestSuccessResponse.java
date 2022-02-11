package server.admin.utils.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestSuccessResponse<T> extends RestResponse<T> {
    T result;

    public RestSuccessResponse(T result) {
        this.code = 200;
        this.result = result;
    }

    public static <T> RestSuccessResponse<T> newInstance(T result) { return new RestSuccessResponse<>(result); }
}
