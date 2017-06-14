package com.blueseasky.springboot.domain.article;

import com.blueseasky.springboot.domain.converter.LocalDateTimeConverter;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by renlei on 2017/6/12.
 */
@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable{


    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTEXT")
    private String context;

    @Column(name = "THUMP_UP_NUM")
    private int thumpUpNum;

    @Column(name = "CREATE_DATE_TIME")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createDateTime;

    @Column(name = "UPDATE_DATE_TIME")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updateDateTime;

    @Version
    @Column
    private int version;


    public Article() {
    }

    public Article(String id, String title, String context, int thumpUpNum) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.thumpUpNum = thumpUpNum;
    }

    public void increaseThumpUpNum(int num){
        this.thumpUpNum +=num;

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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
