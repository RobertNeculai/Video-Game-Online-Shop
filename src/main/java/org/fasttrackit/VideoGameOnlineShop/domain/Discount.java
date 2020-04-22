package org.fasttrackit.VideoGameOnlineShop.domain;

import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.bind.DefaultValue;


import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Embeddable
public class Discount {
    private double fullPrice;
    @Range(min = 0, max = 100)
    private int level;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

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
                .add("fullPrice=" + fullPrice)
                .add("level=" + level)
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .toString();
    }
}
