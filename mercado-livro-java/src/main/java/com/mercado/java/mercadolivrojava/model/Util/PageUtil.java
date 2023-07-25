package com.mercado.java.mercadolivrojava.model.Util;

import com.mercado.java.mercadolivrojava.controller.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageUtil {

    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        List<T> content = page.getContent();
        int currentPage = page.getNumber();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        return new PageResponse<>(content, currentPage, totalItems, totalPages);
    }
}
