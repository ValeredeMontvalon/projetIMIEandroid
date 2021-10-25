package com.example.lorcdi02.applicationandroimie.Feeds;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Latlanh on 01/08/2015.
 */
public class Promotion implements Serializable {
    private Integer promotionId;
    private String promotionName;
    private String promotionNumber;
    private Date promotionDateBegin;
    private Date promotionDateEnd;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getPromotionNumber() {
        return promotionNumber;
    }

    public void setPromotionNumber(String promotionNumber) {
        this.promotionNumber = promotionNumber;
    }

    public Date getPromotionDateBegin() {
        return promotionDateBegin;
    }

    public void setPromotionDateBegin(Date promotionDateBegin) {
        this.promotionDateBegin = promotionDateBegin;
    }

    public Date getPromotionDateEnd() {
        return promotionDateEnd;
    }

    public void setPromotionDateEnd(Date promotionDateEnd) {
        this.promotionDateEnd = promotionDateEnd;
    }
}
