package com.jk.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangjiaming.service.ArticleService;
import com.zhangjm.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleTest {

	@Autowired
	private ArticleService articleService;
	
	@Test
	public void Test2() {
		articleService.getRelArticleList(4, 14, 168, 1);
	}
}

