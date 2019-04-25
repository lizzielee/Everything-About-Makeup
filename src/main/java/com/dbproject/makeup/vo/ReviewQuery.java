/**
 * Copyrigth (C), 2019-2019
 * FileAName:   ReviewQuery
 * Author:
 * Date:        2019-04-20 16:42
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
package com.dbproject.makeup.vo;

public class ReviewQuery {

    private String title;
    private boolean recommend;
    private Long productId;
    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
