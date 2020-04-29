package org.fasttrackit.VideoGameOnlineShop.domain;

import org.hibernate.validator.constraints.Range;


import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Embeddable
public class Discount {
    @Range(min = 0, max = 100)
    private int level;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Discount.class.getSimpleName() + "[", "]")
                .add("level=" + level)
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .toString();
    }
}
