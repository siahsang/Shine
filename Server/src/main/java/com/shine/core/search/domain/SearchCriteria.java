package com.shine.core.search.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class SearchCriteria {

    public final static String PAGE_SIZE_PARAMETER = "pageSize";
    public final static String PAGE_NUMBER_PARAMETER = "page";
    public final static String SORT_PARAMETER = "sort";
    public final static String QUERY_PARAMETER = "q";


    private String query;

    private String sortQuery;

    private Integer page;

    private Integer pageSize;

    private Integer startIndex;

    private Map<String, String[]> filterCriteria = new HashMap<>();


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSortQuery() {
        return sortQuery;
    }

    public void setSortQuery(String sortQuery) {
        this.sortQuery = sortQuery;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Map<String, String[]> getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(Map<String, String[]> filterCriteria) {
        this.filterCriteria = filterCriteria;
    }
}
