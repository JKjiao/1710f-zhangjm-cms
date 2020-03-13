package com.zhangjiaming.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zhangjiaming.pojo.Article;

public interface ArticleDao extends BaseDao<Article>{
	@Select("SELECT id from cms_article")
	List<Integer> selectIdList();
	
	List<Article> selectArticleofImages( Integer commentType);
}
