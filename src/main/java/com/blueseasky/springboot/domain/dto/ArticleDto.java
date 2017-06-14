package com.blueseasky.springboot.domain.dto;

import com.blueseasky.springboot.domain.article.Article;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by renlei on 2017/6/12.
 */
public class ArticleDto {

    private String id;
    private String title;
    private String context;
    private int thumpUpNum;
    private String createDateTime;
    private String updateDateTime;


    public ArticleDto() {
    }

    public ArticleDto(Article article){

        this.setId(article.getId());
        this.setTitle(article.getTitle());
        this.setContext(article.getContext());
        this.setThumpUpNum(article.getThumpUpNum());
        this.setCreateDateTime(article.getCreateDateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        this.setUpdateDateTime(article.getUpdateDateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getThumpUpNum() {
        return thumpUpNum;
    }

    public void setThumpUpNum(int thumpUpNum) {
        this.thumpUpNum = thumpUpNum;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
