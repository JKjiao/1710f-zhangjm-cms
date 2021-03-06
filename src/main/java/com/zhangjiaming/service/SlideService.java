package com.zhangjiaming.service;

import java.util.List;

import com.zhangjiaming.pojo.Slide;

public interface SlideService {
	/**
	 * @Title: getAll   
	 * @Description: 查询所有轮播图   
	 * @param: @return      
	 * @return: List<Slide>      
	 * @throws
	 */
	List<Slide> getAll();
}
