package server.admin.model.common.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Getter
public class CustomPageResult<T> {
    private List<T> content;
    private int totalPages;
    private Long totalElements;
    private Boolean last;
    private int numberOfElements;
    private Boolean first;
    private int size;
    private int page;

    public CustomPageResult(Page<T> result, Pageable pageable){
        this.content = result.getContent();
        this.totalElements = result.getTotalElements();
        this.totalPages = result.getTotalPages();
        this.last = result.isLast();
        this.numberOfElements = result.getNumberOfElements();
        this.first = result.isFirst();
        this.size = result.getContent().size();
        this.page = pageable.getPageNumber();
    }
}
