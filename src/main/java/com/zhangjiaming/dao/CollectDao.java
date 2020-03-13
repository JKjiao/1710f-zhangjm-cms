package com.zhangjiaming.dao;

import org.apache.ibatis.annotations.Param;

import com.zhangjiaming.pojo.Collect;

public interface CollectDao extends BaseDao<Collect>{

	/**
	 * 根据URL和UserID查询此文章是否被用户收藏过
	 * @param url
	 * @param userId
	 * @return
	 */
	Collect selectByTitleAndUserId(@Param("title")String title, @Param("userId")Integer userId);
}
