package com.jk.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jk.dao.SlideDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class SlideTest {

	@Autowired
	private SlideDao slideDao;
	
	@Test
	public void SlideTest2() {
		slideDao.select(null);
	}
	@Test
	public void SlideTest3() {
		slideDao.selectById(2);
	}
	@Test
	public void SlideTest4() {
		slideDao.update(null);
	}
	@Test
	public void SlideTest5() {
		slideDao.insert(null);
	}
	@Test
	public void SlideTest6() {
		slideDao.delete("3");
	}
}
