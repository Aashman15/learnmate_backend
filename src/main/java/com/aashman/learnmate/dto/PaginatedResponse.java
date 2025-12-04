package com.aashman.learnmate.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse <T>{
    private List<T> content;
    private int currentPage;
    private int pageSize;
    private Long totalItems;
    private int  totalPages;
    private boolean hasMore;
}
