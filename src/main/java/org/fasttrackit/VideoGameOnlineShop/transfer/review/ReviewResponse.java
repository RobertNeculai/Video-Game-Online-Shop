package org.fasttrackit.VideoGameOnlineShop.transfer.review;

import java.util.StringJoiner;

public class ReviewResponse {
    private long id;
    private String content;

    @Override
    public String toString() {
        return new StringJoiner(", ", ReviewResponse.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("content='" + content + "'")
                .toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
