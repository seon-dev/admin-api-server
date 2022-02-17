package server.admin.utils.cursor;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Getter
public class CursorResult<T> {
    private T content;
    private Boolean hasNext;
    private String cursor;
    private int size;

    public CursorResult(T content, Boolean hasNext, Long cursorId, int size) {
        String cursor = cursorId.toString();
        this.content = content;
        this.hasNext = hasNext;
        this.cursor = Base64.getEncoder().encodeToString(cursor.getBytes());
        this.size = size;
    }
}