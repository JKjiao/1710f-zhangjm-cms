package com.jk.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangjiaming.dao.UserDao;
import com.zhangjiaming.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class DemoTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testa() {
		User user = new User();
		List<User> list = userDao.select(user);
		System.out.println(list);
	}
	
	@Test 
	public void testSelectById() {
		User sd = userDao.selectById(177);
		System.out.println(sd);
	}
	@Test 
	public void testInsert() {
		userDao.insert(null);
	}
}
