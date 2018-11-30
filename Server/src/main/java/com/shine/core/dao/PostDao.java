package com.shine.core.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.domain.Post;
import com.shine.core.domain.PostType;
import com.shine.core.search.domain.SearchCriteria;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PostDao extends DAO<Post> {

    <T extends Post> List<T> readFilteredPostsByCriteria(SearchCriteria searchCriteria, List<PostType> postType);

}
