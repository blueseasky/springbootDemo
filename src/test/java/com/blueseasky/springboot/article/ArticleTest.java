package com.blueseasky.springboot.article;

import com.blueseasky.springboot.domain.article.Article;
import com.blueseasky.springboot.domain.article.dao.ArticleDao;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by renlei on 2017/6/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ConfigurationProperties(locations = "classpath:config/application.properties")
@Rollback(value = false)
//@Transactional()
public class ArticleTest extends AbstractJUnit4SpringContextTests{

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void test_creatTable(){

        System.out.println("create table");
    }

    @Test
    public void test_insertArticle(){

        Article article = new Article();
        article.setId("1");
        article.setTitle("文章标题1");
        article.setContext("文章正文1");
        article.setThumpUpNum(0);
        article.setCreateDateTime(LocalDateTime.now());
        article.setUpdateDateTime(LocalDateTime.now());

        Article articleSave = articleDao.save(article);

        assert articleSave.getId() == "1";
        assert articleSave.getTitle() == "文章标题1";

    }

    @Test
    public void test_queryArticle(){

        Article article = articleDao.getOne("1");

        assert article.getTitle() == "文章标题1";


    }




}
