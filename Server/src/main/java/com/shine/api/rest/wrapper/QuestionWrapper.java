package com.shine.api.rest.wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shine.common.rest.api.wrapper.APIUnWrapper;
import com.shine.common.rest.api.wrapper.APIWrapper;
import com.shine.common.rest.api.wrapper.BaseWrapper;
import com.shine.core.domain.Question;
import com.shine.core.domain.Tag;
import com.shine.core.service.QuestionService;
import com.shine.core.service.TagService;
import com.shine.core.service.TagServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("QuestionWrapper")
@Scope("prototype")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class QuestionWrapper extends BaseWrapper implements APIUnWrapper<Question>, APIWrapper<Question> {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String body;

    @JsonProperty
    private Long vote;

    @JsonProperty(value = "answer_count")
    private Long answerCount;


    @JsonProperty(value = "tag_names")
    private List<String> tagNames = new ArrayList<>();


    @Resource
    private TagService tagService;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(ArrayList<String> tagNames) {
        this.tagNames = tagNames;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
    }

    @Override
    public Question unwrap(HttpServletRequest request, ApplicationContext context) {
        final TagService tagService = context.getBean(TagServiceImpl.class);
        final QuestionService questionService = context.getBean(QuestionService.class);
        tagService.createNotExistTags(this.tagNames);

        List<Tag> tagList = tagService.findTagsByName(this.tagNames);

        Question question = questionService.createQuestionFromId(this.id);
        question.setBody(body);
        question.setTitle(title);
        question.setVote(vote);
        question.setTagList(tagList);

        return question;
    }

    @Override
    public void wrap(Question model, HttpServletRequest request) {
        List<Tag> tagList = tagService.findTagsForQuestion(model);

        this.id = model.getId();
        this.title = model.getTitle();
        this.body = model.getBody();
        this.vote = model.getVote();
        this.answerCount = model.getAnswerCount();
        this.tagNames = tagList
                .stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }

}
