package server.admin.model.common.rest;

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

    public RestSuccessResponse(){ this.code = 200; }

    public static <T> RestSuccessResponse<T> newInstance(T result) { return new RestSuccessResponse<>(result); }

    public static <T> RestSuccessResponse<T> empty() { return new RestSuccessResponse<>(); }
}
