package com.ranoshisdas.poostaar.payload;

import com.ranoshisdas.poostaar.entity.Post;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class Response {
    private List<?> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;
}
