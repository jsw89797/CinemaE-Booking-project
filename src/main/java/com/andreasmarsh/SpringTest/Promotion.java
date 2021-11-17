package com.andreasmarsh.SpringTest;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionID;

    @Column(nullable = false, name = "percentage", length = 2)
    private Long percentage;

    @Temporal(TemporalType.DATE)
    private java.util.Date startDate;

    @Temporal(TemporalType.TIME)
    private java.util.Date startTime;

    @Temporal(TemporalType.DATE)
    private java.util.Date endDate;

    @Temporal(TemporalType.TIME)
    private java.util.Date endTime;

    public Promotion() {

    }

    public Promotion(Long promotionID, Long percentage, Date startDate, Date startTime, Date endDate, Date endTime) {
        this.promotionID = promotionID;
        this.percentage = percentage;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public Promotion(Long percentage, Date startDate, Date startTime, Date endDate, Date endTime) {
        this.percentage = percentage;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public Long getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(Long promotionID) {
        this.promotionID = promotionID;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
