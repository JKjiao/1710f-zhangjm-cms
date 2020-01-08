package com.jk.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jk.dao.ChannelDao;
import com.jk.dao.SildeDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ChannelTest {
	
	@Autowired
	private ChannelDao channeDao;
	
	@Test
	public void SlideTest2() {
		channeDao.select(null);
	}
	@Test
	public void SlideTest3() {
		channeDao.selectById(2);
	}
	@Test
	public void SlideTest4() {
		channeDao.update(null);
	}
	@Test
	public void SlideTest5() {
		channeDao.insert(null);
	}
	@Test
	public void SlideTest6() {
		channeDao.delete("3");
	}
}
