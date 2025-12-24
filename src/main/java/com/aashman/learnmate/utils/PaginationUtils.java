package com.aashman.learnmate.utils;

import com.aashman.learnmate.dto.MyPageRequest;
import com.aashman.learnmate.dto.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PaginationUtils {
    public static PageRequest getPageRequest(MyPageRequest myPageRequest, Sort sort) {
        return PageRequest.of(myPageRequest.getPage() - 1, myPageRequest.getPageSize(), sort);
    }

    public static PageRequest getPageRequest(int page, int pageSize, Sort sort) {
        return PageRequest.of(page - 1, pageSize, sort);
    }

    public static <T> PaginatedResponse<T> createPaginatedResponse(Page<T> page) {
        PaginatedResponse<T> response = new PaginatedResponse<>();
        response.setCurrentPage(page.getNumber() + 1);
        response.setTotalItems(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setPageSize(page.getSize());
        response.setContent(page.getContent());
        response.setHasMore(page.hasNext());
        return response;
    }
}
