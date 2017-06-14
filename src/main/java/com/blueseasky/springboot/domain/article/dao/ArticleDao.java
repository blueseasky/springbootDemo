package com.blueseasky.springboot.domain.article.dao;

import com.blueseasky.springboot.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by renlei on 2017/6/13.
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article>{



}
