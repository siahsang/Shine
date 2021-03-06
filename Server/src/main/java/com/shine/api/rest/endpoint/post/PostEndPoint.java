package com.shine.api.rest.endpoint.post;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.wrapper.SearchResultWrapper;
import com.shine.core.search.ShineSearchService;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchResult;
import com.shine.web.search.SearchServiceDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "/post")
public class PostEndPoint extends BaseEndpoint {

    @Resource
    private SearchServiceDTO searchServiceDTO;

    @Resource(name = "databaseSearchServiceImpl")
    private ShineSearchService shineSearchService;

    @RequestMapping(path = "/search")
    public SearchResultWrapper searchInPosts(HttpServletRequest httpServletRequest) {
        SearchCriteria searchCriteria = searchServiceDTO.buildSearchCriteria(httpServletRequest);
        SearchResult searchResult = shineSearchService.searchPosts(searchCriteria);

        SearchResultWrapper searchResultWrapper = applicationContext.getBean(SearchResultWrapper.class);
        searchResultWrapper.wrap(searchResult, httpServletRequest);

        return searchResultWrapper;
    }


}
