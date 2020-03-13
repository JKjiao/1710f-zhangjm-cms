package com.jk.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.zhangjiaming.pojo.Article;
import com.zhangjiaming.respository.ArticleRespository;
import com.zhangjiaming.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
/**
 * 同步文章到索引库
 *
 */
public class ArticleRespositoryTest {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleRespository articleRespository;
	
	
	@Test
	public void articleToIndexTest() {
		/** 分页读取文章**/
		for(int  pageNum = 1; pageNum < 10000; pageNum ++) {
			/** 文章审核通过，同步到索引库且设置状态为存在 **/
			Article article = new Article();
			article.setStatus(1);
			article.setDeleted(0);
			PageInfo<Article> pageInfo = articleService.getPageInfo(article, pageNum, 10);
			/** 如果文章读取完毕，则结束循环，退出测试 **/
			if(pageInfo == null || pageInfo.getList().size() == 0) {
				break;
			}
			/** 将审核通过的文章同步到索引库 **/
			for (Article a : pageInfo.getList()) {
				articleRespository.save(a);
			}
		}
		
	}
}
