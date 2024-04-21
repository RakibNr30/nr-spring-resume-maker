package com.resume.dto;

import com.resume.util.StringUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequestDto {
    private String page = "0";

    private String size = "10";

    private String direction = "asc";

    private String sort = "id";

    private String search = "";

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Pageable getPageable() {

        int pageNumber = Math.max(StringUtil.toInt(this.page) - 1, 0);
        int pageSize = Math.max(StringUtil.toInt(this.size), 1);
        Sort.Direction sortDirection = Sort.Direction.fromString(this.direction.equalsIgnoreCase("desc") ? this.direction : "asc");
        String sortField = this.sort;

        return PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField));
    }
}
