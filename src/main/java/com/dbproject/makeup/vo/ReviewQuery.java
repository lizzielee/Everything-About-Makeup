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
    private Long typeId;
    private boolean recommend;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
