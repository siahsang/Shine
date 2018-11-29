package com.shine.web;

import com.shine.core.search.domain.SearchCriteria;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andre Azzolini (apazzolini)
 */

public interface SearcServiceDTO {

    SearchCriteria buildSearchCriteria(HttpServletRequest httpServletRequest);

}