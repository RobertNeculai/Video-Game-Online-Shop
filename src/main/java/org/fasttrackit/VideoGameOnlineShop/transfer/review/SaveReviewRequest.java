package org.fasttrackit.VideoGameOnlineShop.transfer.review;

import java.util.StringJoiner;

public class SaveReviewRequest {

    private String content;
    private int rating;


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", org.fasttrackit.VideoGameOnlineShop.transfer.review.ReviewResponse.class.getSimpleName() + "[", "]")
                .add("content='" + content + "'")
                .toString();
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
