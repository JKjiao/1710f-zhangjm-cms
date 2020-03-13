package com.zhangjiaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangjiaming.dao.SlideDao;
import com.zhangjiaming.pojo.Slide;
import com.zhangjiaming.service.SlideService;



@Service
public class SlideServiceImpl implements SlideService{
	@Autowired
	private SlideDao slideDao;

	@Override
	public List<Slide> getAll() {
		return slideDao.select(null);
	}
	

}
