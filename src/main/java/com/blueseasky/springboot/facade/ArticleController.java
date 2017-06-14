package com.blueseasky.springboot.facade;

import com.blueseasky.springboot.domain.article.Article;
import com.blueseasky.springboot.domain.article.ArticleWrapper;
import com.blueseasky.springboot.domain.article.dao.ArticleDao;
import com.blueseasky.springboot.domain.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renlei on 2017/6/12.
 */
@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 查询所有文章接口
     * 默认排序 按点赞数量相同数量按创建日期
     * @return
     */
    @GetMapping(value = "/queryArticleList",produces = "application/json;charset=UTF-8")
    public List<ArticleDto> queryArticleList(){

        List<Article> articleList =  articleDao.findAll();

        ArticleWrapper articleWrapper = new ArticleWrapper(articleList);


        return articleWrapper.convert2Dto();
    }

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @GetMapping(value = "/queryArticle", produces = "application/json;charset=UTF-8")
    public ArticleDto queryArticle(@ModelAttribute String id){

        System.out.println("queryArticle start id="+id);
        if (null == id){
            return null;
        }

        Article article = articleDao.findOne(id);

        return new ArticleDto(article);
    }

    /**
     * 根据id，点赞文章
     * @param id
     * @return
     */
    @PutMapping(value = "/thumpUpArticle", produces = "application/json")
    public ArticleDto thumpUpArticle(@ModelAttribute String id){

        if (null == id){
            return null;
        }

        Article article = articleDao.findOne(id);

        article.increaseThumpUpNum(1);

        Article articleUpdate = articleDao.save(article);

        return new ArticleDto(articleUpdate);
    }


}

