package com.jk.service.impl;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.unit.DataUnit;

import com.zhangjiaming.pojo.Comment;
import com.zhangjiaming.service.CommentService;
import com.zhangjm.util.DateUtil;
import com.zhangjm.util.RandomUtil;
import com.zhangjm.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class CommentServiceImplTest {

	@Autowired
	private CommentService commentService;
	
	@Test
	public void test() {
		int id = 6;
		for (int i = 0; i < 1000; i++) {
			Comment comment = new Comment();
			comment.setArticleId(RandomUtil.random(166,171));
			comment.setContent(StringUtil.randomChineseString());
			comment.setId(id);
			// 4 发布时间从2019-1-1 00:00:00至今随机（5分）
			Calendar calendar = Calendar.getInstance();
			calendar.set(2019, 0, 1);//2019-1-1 00:00:00
			  //调用工具类
		}
	}

}
