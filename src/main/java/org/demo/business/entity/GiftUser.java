package org.demo.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

@TableName("gift_user")
public class GiftUser extends Model<GiftUser> {
    private String giftArticleId;

    private String userId;

    private Date createTime;

    public String getGiftArticleId() {
        return giftArticleId;
    }

    public void setGiftArticleId(String giftArticleId) {
        this.giftArticleId = giftArticleId == null ? null : giftArticleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}