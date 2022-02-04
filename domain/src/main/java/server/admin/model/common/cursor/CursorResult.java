package server.admin.model.common.cursor;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class CursorResult<T> {
    private Page<T> values;
    private Boolean hasNext;

    public CursorResult(Page<T> values, Boolean hasNext) {
        this.values = values;
        this.hasNext = hasNext;
    }
}