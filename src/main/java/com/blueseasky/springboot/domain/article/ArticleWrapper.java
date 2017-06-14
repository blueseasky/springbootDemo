package com.blueseasky.springboot.domain.article;

import com.blueseasky.springboot.domain.dto.ArticleDto;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renlei on 2017/6/13.
 */
public class ArticleWrapper {


    private List<Article> articles;

    public ArticleWrapper() {
    }

    public ArticleWrapper(List<Article> articles) {
        this.articles = articles;
    }

    public List<ArticleDto>  convert2Dto(){
        List<ArticleDto> articleDtos = new ArrayList<ArticleDto>();

        if (!CollectionUtils.isEmpty(this.articles)){

            for (Article article : articles) {
                articleDtos.add(new ArticleDto(article));
            }

        }

        return articleDtos;
    }



}
