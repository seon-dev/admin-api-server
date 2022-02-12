package server.admin.common.exception.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final Timestamp timestamp;
    private final String statusCode;
    private final String description;
    private final HttpStatus status;

    public ErrorResponse(String statusCode, String description, HttpStatus status) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.statusCode = statusCode;
        this.description = description;
        this.status = status;
    }
}
