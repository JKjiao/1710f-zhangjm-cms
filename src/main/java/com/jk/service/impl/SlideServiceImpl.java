package com.jk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.SlideDao;
import com.jk.pojo.Slide;
import com.jk.service.SlideService;



@Service
public class SlideServiceImpl implements SlideService{
	@Autowired
	private SlideDao slideDao;

	@Override
	public List<Slide> getAll() {
		return slideDao.select(null);
	}
	

}
