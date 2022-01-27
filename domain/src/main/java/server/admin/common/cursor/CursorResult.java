package server.admin.common.cursor;

import org.springframework.data.domain.Page;

public class CursorResult<T> {
    private Page<T> values;
    private Boolean hasNext;

    public CursorResult(Page<T> values, Boolean hasNext) {
        this.values = values;
        this.hasNext = hasNext;
    }
}