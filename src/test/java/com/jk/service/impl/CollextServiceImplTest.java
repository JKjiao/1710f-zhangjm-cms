package com.jk.service.impl;

import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.zhangjiaming.pojo.Collect;
import com.zhangjiaming.service.CollectService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class CollextServiceImplTest {

	@Resource
	CollectService collectService;
	
	@Test
	public void testInsert() {
		Collect collect = new Collect();
		collect.setUser_id(238);
		collect.setText("test收集2");
		collect.setUrl("avaidusdm");
		collect.setCreated(new Date());
		int i = collectService.insert(collect);
		System.out.println(i);
	}

	
	@Test
	public void testDelete() {
		int i = collectService.delete("23");
		System.out.println(i);
	}
	
	@Test
	public void testSelete() {
		PageInfo<Collect> info = collectService.selects(238, 1, 3);
		System.out.println(info);
	}
	
}
