package com.shine.core.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.domain.Answer;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface AnswerDao extends DAO<Answer> {
    List<Answer> readAnswer(int offset, int limit);

    List<Answer> readAnswerForQuestions(Long questionId, int offset, int limit);
}
