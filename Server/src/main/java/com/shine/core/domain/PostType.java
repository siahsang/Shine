package com.shine.core.domain;

import java.util.EnumSet;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum PostType {
    POST("Post"), QUESTION("Question"), ANSWER("Answer");

    public String type;

    PostType(String type) {
        this.type = type;
    }

    public static PostType getPostType(final String postType) {
        for (PostType post : EnumSet.allOf(PostType.class)) {
            if (post.type.equalsIgnoreCase(postType)) {
                return post;
            }
        }

        return null;
    }
}
