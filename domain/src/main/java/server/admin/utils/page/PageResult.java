package server.admin.utils.page;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Getter
public class PageResult<T> {
    private List<T> content;
//    private int totalPages;
    private Long totalElements;
//    private Boolean last;
//    private int numberOfElements;
//    private Boolean first;
//    private int size;
//    private int page;

    public PageResult(Page<T> result){
        this.content = result.getContent();
        this.totalElements = result.getTotalElements();
//        this.totalPages = result.getTotalPages();
//        this.last = result.isLast();
//        this.numberOfElements = result.getNumberOfElements();
//        this.first = result.isFirst();
//        this.size = result.getContent().size();
//        this.page = result.getPageable().getPageNumber();

    }
}
