package com.service.levain.domain.dto.page.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
    }
}
